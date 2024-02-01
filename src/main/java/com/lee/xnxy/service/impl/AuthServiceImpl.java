package com.lee.xnxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.xnxy.model.dao.home.Auth;
import com.lee.xnxy.service.AuthService;
import com.lee.xnxy.mapper.AuthMapper;
import org.springframework.stereotype.Service;

/**
* @author 20882
* @description 针对表【xnxy_auth】的数据库操作Service实现
* @createDate 2023-12-09 21:31:01
*/
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth>
    implements AuthService{

}




