package com.lee.xnxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.xnxy.model.bizRequest.home.SaveUserInfoBizRequest;
import com.lee.xnxy.model.dao.home.User;

/**
* @author 晓龙coding
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-02-02 22:13:38
* @Entity com.lee.xnxydev.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {


    void saveUserInfo(SaveUserInfoBizRequest saveUserInfoBizRequest);
}




