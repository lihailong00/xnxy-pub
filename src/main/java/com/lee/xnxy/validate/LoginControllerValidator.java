package com.lee.xnxy.validate;

import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.request.login.LoginJwcRequest;
import com.lee.xnxy.util.UserContextDTOUtil;
import com.lee.xnxy.validate.module.AlreadyLoginJwcValidateModule;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginControllerValidator {
    @Resource
    private AlreadyLoginJwcValidateModule alreadyLoginJwcValidateModule;
    public void loginJwc(LoginJwcRequest loginJwcRequest) {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        alreadyLoginJwcValidateModule.validate(userId);
    }
}
