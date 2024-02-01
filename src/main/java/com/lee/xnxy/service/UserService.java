package com.lee.xnxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.model.dao.home.User;

/**
* @author 晓龙coding
* @description 针对表【user】的数据库操作Service
* @createDate 2023-02-02 22:13:38
*/
public interface UserService extends IService<User> {
    String getJwcUsernameByUserId(Long userId) throws BizException;
}
