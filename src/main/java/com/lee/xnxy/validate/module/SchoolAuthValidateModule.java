package com.lee.xnxy.validate.module;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.enums.AuthEnums;
import com.lee.xnxy.exception.ValidateException;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.dao.home.Auth;
import com.lee.xnxy.service.AuthService;
import com.lee.xnxy.service.UserService;
import com.lee.xnxy.util.UserContextDTOUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SchoolAuthValidateModule {
    @Resource
    private UserService userService;

    @Resource
    private AuthService authService;

    public void validate() {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();

        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.eq(Auth::getUserId, userId).eq(Auth::getCode, AuthEnums.JWC_ACCREDITED);
        List<Auth> authList = authService.list(authLambdaQueryWrapper);
        if (CollectionUtils.isEmpty(authList)) {
            throw new ValidateException(CommonConstant.NOT_LOGIN_JWC);
        }
    }
}
