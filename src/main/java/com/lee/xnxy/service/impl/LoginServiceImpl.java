package com.lee.xnxy.service.impl;
import java.time.LocalDateTime;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.xnxy.aop.LogAnnotation;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.enums.AuthEnums;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.login.LoginJwcBizRequest;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.dto.login.LoginWXDTO;
import com.lee.xnxy.model.dao.home.Auth;
import com.lee.xnxy.model.dto.login.JwtTokenData;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.model.remote.FirstLoginJwcRespTO;
import com.lee.xnxy.service.AuthService;
import com.lee.xnxy.service.CourseService;
import com.lee.xnxy.service.LoginService;
import com.lee.xnxy.service.UserService;
import com.lee.xnxy.util.GeneratorUtil;
import com.lee.xnxy.util.JWTUtil;
import com.lee.xnxy.util.UserContextDTOUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 晓龙coding
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserService userService;

    @Resource
    private AuthService authService;

    @Value("${verifyUrl}")
    private String verifyUrl;

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Value("${wx.raw-url}")
    private String wxRawUrl;

    @Resource
    private CourseService courseService;

    @Override
    @LogAnnotation(module="登录", operator="微信登录（service层）")
    public ResponseResult loginWX(String code) throws BizException {
        String replaceUrl = wxRawUrl.replace("{0}", appId).replace("{1}", appSecret).replace("{2}", code);
        // 向微信服务器发送请求
        String wxServerResponse = HttpUtil.get(replaceUrl);
        if (wxServerResponse.contains(CommonConstant.WX_SERVER_ERROR_MESSAGE)) {
            return ResponseResult.fail(CommonConstant.WX_SERVER_FAILURE_RESPONSE);
        }

        // 解析微信服务器返回的字符串信息到map
        ObjectMapper objectMapper = new ObjectMapper();
        Map wxServerResponseMap = null;
        try {
            wxServerResponseMap = objectMapper.readValue(wxServerResponse, Map.class);
        } catch (JsonProcessingException e) {
            throw new BizException("反序列化微信服务器信息异常", e);
        }

        // 查询openid是否存在
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(User::getOpenid, wxServerResponseMap.get(CommonConstant.OPENID));
        User one = userService.getOne(userQueryWrapper);
        // 如果openid不存在，则注册新用户
        if (one == null) {
            userService.save(generateNewUser(wxServerResponseMap));
        }

        // 查找该用户的uId
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(User::getOpenid, wxServerResponseMap.get(CommonConstant.OPENID));
        queryWrapper1.select(User::getUId, User::getUsername, User::getPhotoImg, User::getMoney);
        User user = userService.getOne(queryWrapper1);

        // 查询用户是否登陆教务处
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, user.getUId()).eq(Auth::getCode, CommonConstant.IS_LOGIN_JWC);
        Auth auth = authService.getOne(authLambdaQueryWrapper);
        boolean isLoginJwc = false;
        if (auth != null) {
            isLoginJwc = true;
        }

        // 生成JWT token 并返回给前端
        LoginWXDTO loginWXDTO = new LoginWXDTO();
        String token = setJwtTokenWithParam(user);
        loginWXDTO.setToken(token);
        loginWXDTO.setUserId(user.getUId().toString());
        loginWXDTO.setUsername(user.getUsername());
        loginWXDTO.setIsLoginJwc(isLoginJwc);
        return ResponseResult.success(loginWXDTO);
    }

    @Override
    public ResponseResult loginJwc(LoginJwcBizRequest loginJwcBizRequest) throws SysException {
        Long userId = loginJwcBizRequest.getUserId();
        Map<String, Object> verifyParam = new HashMap<>();
        verifyParam.put(CommonConstant.USERNAME, loginJwcBizRequest.getUsername());
        verifyParam.put(CommonConstant.PASSWORD, loginJwcBizRequest.getPassword());
        verifyParam.put("user_id", userId);

        String result;
        try {
            result = HttpUtil.post(verifyUrl, verifyParam);
        } catch (Exception e) {
            throw new SysException(CommonConstant.ERROR_CRAWLER, e);
        }
        try {
            FirstLoginJwcRespTO firstLoginJwcRespDataTO = JSONUtil.toBean(result, FirstLoginJwcRespTO.class);
            if (firstLoginJwcRespDataTO.getCode() != 0) {
                return ResponseResult.fail(firstLoginJwcRespDataTO.getMsg());
            }
            courseService.savePersonJwcInfo(firstLoginJwcRespDataTO);
            updateUserJwcInfo(loginJwcBizRequest.getUsername(), loginJwcBizRequest.getPassword());
            return ResponseResult.success(null, CommonConstant.JWC_VERIFY_SUCCESS);
        } catch (Exception e) {
            throw new SysException(CommonConstant.ERROR_CRAWLER_PARSE, e);
        }
    }

    @Override
    public ResponseResult loginJwcForSpyder(LoginJwcBizRequest loginJwcBizRequest) throws SysException {
        Long userId = loginJwcBizRequest.getUserId();
        Map<String, Object> verifyParam = new HashMap<>();
        verifyParam.put(CommonConstant.USERNAME, loginJwcBizRequest.getUsername());
        verifyParam.put(CommonConstant.PASSWORD, loginJwcBizRequest.getPassword());
        verifyParam.put("user_id", userId);

        String result;
        try {
            result = HttpUtil.post(verifyUrl, verifyParam);
        } catch (Exception e) {
            throw new SysException(CommonConstant.ERROR_CRAWLER, e);
        }
        try {
            FirstLoginJwcRespTO firstLoginJwcRespDataTO = JSONUtil.toBean(result, FirstLoginJwcRespTO.class);
            if (firstLoginJwcRespDataTO.getCode() != 0) {
                return ResponseResult.fail(firstLoginJwcRespDataTO.getMsg());
            }
            courseService.savePersonJwcInfo(firstLoginJwcRespDataTO);
            // 爬虫获取的数据无需保存到xnxy_user表
//            updateUserJwcInfo(loginJwcBizRequest.getUsername(), loginJwcBizRequest.getPassword());
            return ResponseResult.success(null, CommonConstant.JWC_VERIFY_SUCCESS);
        } catch (Exception e) {
            throw new SysException(CommonConstant.ERROR_CRAWLER_PARSE, e);
        }
    }

    @Override
    public ResponseResult logoutJwc(Long userId) {
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, userId).eq(Auth::getCode, AuthEnums.JWC_ACCREDITED.getCode());
        boolean removed = authService.remove(authLambdaQueryWrapper);

        // 清空用户的教务处信息
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.set(User::getJwcUsername, "").set(User::getJwcPassword, "").eq(User::getUId, userId);
        userService.update(userLambdaUpdateWrapper);

        if (removed) {
            return ResponseResult.success(null, CommonConstant.LOGOUT_JWC);
        }
        return ResponseResult.success(null, CommonConstant.NOT_LOGIN_JWC);
    }

    @Override
    public ResponseResult isLoginJwc(Long userId) {
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, userId);
        List<Auth> list = authService.list(authLambdaQueryWrapper);
        if (CollectionUtil.isEmpty(list)) {
            return ResponseResult.fail(CommonConstant.NOT_LOGIN_JWC);
        }
        return ResponseResult.success();
    }

    private void updateUserJwcInfo(String jwcUsername, String password) {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getUId, userId);
        lambdaUpdateWrapper
                .set(User::getJwcUsername, jwcUsername)
                .set(User::getJwcPassword, password);
        userService.update(lambdaUpdateWrapper);

        Auth auth = new Auth();
        auth.setUserId(userId);
        auth.setCode(AuthEnums.JWC_ACCREDITED.getCode());
        authService.save(auth);
    }

    private User generateNewUser(Map resp) {
        User user = new User();
        // 不指定uId，mybatis-plus会按照雪花算法自动生成一个uId
        user.setUsername(GeneratorUtil.generateDefaultUsername());
        user.setSessionKey(resp.get(CommonConstant.SESSION_KEY).toString());
        user.setOpenid(resp.get(CommonConstant.OPENID).toString());
        user.setPhotoImg(CommonConstant.DEFAULT_AVATAR);
        user.setCreateTime(LocalDateTime.now());
        user.setHasDeleted(0);
        user.setMoney(CommonConstant.DEFAULT_MONEY);
        return user;
    }

    private String setJwtTokenWithParam(User user) {
        JwtTokenData jwtTokenData = new JwtTokenData();
        jwtTokenData.setUId(user.getUId().toString());
        jwtTokenData.setUsername(user.getUsername());
        jwtTokenData.setPhotoImg(user.getPhotoImg());
        jwtTokenData.setMoney(user.getMoney().toString());
        String jwtTokenDataString = JSON.toJSONString(jwtTokenData);
        Map<String, String> jwtTokenDataMap = JSON.parseObject(jwtTokenDataString, Map.class);
        return JWTUtil.getToken(jwtTokenDataMap, CommonConstant.DEFAULT_TOKEN_VALID_TIME);
    }
}
