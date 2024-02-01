package com.lee.xnxy.controller;

import com.lee.xnxy.aop.DynamicValidate;
import com.lee.xnxy.aop.LogAnnotation;
import com.lee.xnxy.converter.login.LoginJwcBizRequestConverter;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.login.LoginJwcBizRequest;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.request.login.LoginJwcRequest;
import com.lee.xnxy.model.request.login.LoginWxRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.LoginService;
import com.lee.xnxy.util.UserContextDTOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 晓龙coding
 */
@RestController
@RequestMapping("login")
@Api("登录模块")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("wx")
    @LogAnnotation(module="登录", operator="微信登录")
    @ApiOperation("微信用户登录")
    public ResponseResult loginWX(@RequestBody @Valid LoginWxRequest loginWxRequest) throws BizException {
        String code = loginWxRequest.getJsCode();
        return loginService.loginWX(code);
    }

    @DynamicValidate
    @PostMapping("jwc")
    @LogAnnotation(module="认证", operator="登录教务处认证")
    @ApiOperation("教务处登录")
    public ResponseResult loginJwc(@RequestBody @Valid LoginJwcRequest loginJwcRequest) throws SysException {
        LoginJwcBizRequest loginJwcBizRequest = LoginJwcBizRequestConverter.toLoginJwcBizRequest(loginJwcRequest);
        return loginService.loginJwc(loginJwcBizRequest);
    }

    @PostMapping("out")
    @ApiOperation("退出教务处系统")
    public ResponseResult logoutJwc() {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        return loginService.logoutJwc(userId);
    }

    @PostMapping("is-login-jwc")
    @ApiOperation("查看是否登陆教务处")
    public ResponseResult isLoginJwc() {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        return loginService.isLoginJwc(userId);
    }
}