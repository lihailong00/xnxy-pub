package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.enums.AuthEnums;
import com.lee.xnxy.enums.GenderEnums;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.mapper.UserMapper;
import com.lee.xnxy.model.bizRequest.home.SaveUserInfoBizRequest;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.dto.home.GetUserInfoDTO;
import com.lee.xnxy.model.dao.home.Auth;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.AuthService;
import com.lee.xnxy.service.HomeService;
import com.lee.xnxy.service.UserService;
import com.lee.xnxy.util.UserContextDTOUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthService authService;

    @Override
    public ResponseResult getUserInfo(Long userId) throws BizException {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUId, userId);
        List<User> userList = userService.list(userLambdaQueryWrapper);
        if (userList.isEmpty()) {
            throw new BizException("通过用户id没有查询到用户信息, userId=" + userId);
        } else if (userList.size() > 1) {
            throw new BizException("通过用户id查询到多个相同用户，不满足预期，userId=" + userId);
        }
        User user = userList.get(0);
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, userId).eq(Auth::getCode, AuthEnums.JWC_ACCREDITED.getCode());
        List<Auth> authList = authService.list(authLambdaQueryWrapper);
        Auth auth = new Auth();
        if (! CollectionUtils.isEmpty(authList)) {
            auth = authList.get(0);
        }
        GetUserInfoDTO getUserInfoDTO = toGetUserInfoDTO(user, auth);
        return ResponseResult.success(getUserInfoDTO);
    }

    @Override
    public ResponseResult saveUserInfo(SaveUserInfoBizRequest saveUserInfoBizRequest) {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        // 先检查当前用户名是否存在
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, saveUserInfoBizRequest.getUsername()).ne(User::getUId, userId);
        User user = userService.getOne(lambdaQueryWrapper);
        if (Objects.isNull(user)) {
            userMapper.saveUserInfo(saveUserInfoBizRequest);
            return ResponseResult.success();
        } else {
            return ResponseResult.fail(CommonConstant.USERNAME_EXIST);
        }
    }

    private GetUserInfoDTO toGetUserInfoDTO(User user, Auth auth) {
        if (Objects.isNull(user)) {
            return new GetUserInfoDTO();
        }
        GetUserInfoDTO getUserInfoDTO = new GetUserInfoDTO();
        getUserInfoDTO.setUserId(String.valueOf(user.getUId()));
        getUserInfoDTO.setUsername(user.getUsername());
        getUserInfoDTO.setJwcUsername(user.getJwcUsername());
        getUserInfoDTO.setMoney(user.getMoney());
        getUserInfoDTO.setPhotoImg(user.getPhotoImg());
        getUserInfoDTO.setGender(GenderEnums.fromKeys(user.getGender()));
        getUserInfoDTO.setPower(AuthEnums.fromKeys(auth.getCode()));
        return getUserInfoDTO;
    }
}
