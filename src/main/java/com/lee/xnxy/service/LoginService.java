package com.lee.xnxy.service;

import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.login.LoginJwcBizRequest;
import com.lee.xnxy.model.dto.ResponseResult;

/**
 * @author 晓龙coding
 */
public interface LoginService {

    ResponseResult loginWX(String code) throws BizException;

    ResponseResult loginJwc(LoginJwcBizRequest loginJwcBizRequest) throws SysException;

    ResponseResult loginJwcForSpyder(LoginJwcBizRequest loginJwcBizRequest) throws SysException;


    ResponseResult logoutJwc(Long userId);

    ResponseResult isLoginJwc(Long userId);
}
