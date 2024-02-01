package com.lee.xnxy.validate.module;

import cn.hutool.core.lang.Validator;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidEmailModule {
    public void validate(String email) {
        if (Validator.isEmail(email)) {
            return;
        }
        throw new ValidateException(CommonConstant.INVALID_EMAIL);
    }
}
