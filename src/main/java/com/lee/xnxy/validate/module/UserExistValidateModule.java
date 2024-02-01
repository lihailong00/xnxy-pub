package com.lee.xnxy.validate.module;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.ValidateException;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserExistValidateModule {
    @Resource
    private UserService userService;

    public void validate(Long userId) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getUId).eq(User::getUId, userId);
        User user = userService.getOne(userLambdaQueryWrapper);
        if (user == null) {
            throw new ValidateException(CommonConstant.USER_ID_NOT_EXIST);
        }
    }
}
