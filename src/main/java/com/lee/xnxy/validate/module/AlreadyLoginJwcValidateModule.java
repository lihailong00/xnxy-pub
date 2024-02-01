package com.lee.xnxy.validate.module;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.enums.AuthEnums;
import com.lee.xnxy.exception.ValidateException;
import com.lee.xnxy.model.dao.home.Auth;
import com.lee.xnxy.service.AuthService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AlreadyLoginJwcValidateModule {
    @Resource
    private AuthService authService;

    public void validate(Long userId) {
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, userId).eq(Auth::getCode, AuthEnums.JWC_ACCREDITED.getCode());
        List<Auth> authList = authService.list(authLambdaQueryWrapper);
        if (! CollectionUtils.isEmpty(authList)) {
            throw new ValidateException(CommonConstant.ALREADY_LOGIN_JWC);
        }
    }
}
