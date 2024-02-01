package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.service.UserService;
import com.lee.xnxy.mapper.UserMapper;
import org.springframework.stereotype.Service;


/**
* @author 晓龙coding
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-02-02 22:13:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    public String getJwcUsernameByUserId(Long userId) throws BizException {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUId, userId);
        User user = this.getOne(userLambdaQueryWrapper);
        if (user == null) {
            throw new BizException(CommonConstant.EMPTY_JWC_USERNAME);
        }
        return user.getJwcUsername();
    }
}
