package com.lee.xnxy.validate.module;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.xnxy.constant.ValidateFailureConstant;
import com.lee.xnxy.enums.AuthEnums;
import com.lee.xnxy.exception.ValidateException;
import com.lee.xnxy.model.dao.home.Auth;
import com.lee.xnxy.service.AuthService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
public class HasRightOperateValidateModule {
    @Resource
    private AuthService authService;

    public void validate(Long operatorUserId, Long belongUserId) {
        // 所属同一个人，则可以操作
        if (Objects.equals(operatorUserId, belongUserId)) {
            return;
        }

        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, operatorUserId).eq(Auth::getCode, AuthEnums.ADMIN.getCode());
        List<Auth> authList = authService.list(authLambdaQueryWrapper);
        if (CollectionUtils.isEmpty(authList)) {
            throw new ValidateException(ValidateFailureConstant.PERMISSION_DENIED);
        }
    }
}
