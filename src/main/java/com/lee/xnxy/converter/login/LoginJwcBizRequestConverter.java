package com.lee.xnxy.converter.login;

import com.lee.xnxy.model.bizRequest.login.LoginJwcBizRequest;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.request.login.LoginJwcRequest;
import com.lee.xnxy.util.UserContextDTOUtil;

public class LoginJwcBizRequestConverter {
    private LoginJwcBizRequestConverter() {

    }

    public static LoginJwcBizRequest toLoginJwcBizRequest(LoginJwcRequest loginJwcRequest) {
        LoginJwcBizRequest loginJwcBizRequest = new LoginJwcBizRequest();
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        loginJwcBizRequest.setUserId(userId);
        loginJwcBizRequest.setUsername(loginJwcRequest.getUsername());
        loginJwcBizRequest.setPassword(loginJwcRequest.getPassword());
        return loginJwcBizRequest;
    }
}
