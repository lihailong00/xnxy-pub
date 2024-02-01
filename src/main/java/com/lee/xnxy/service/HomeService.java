package com.lee.xnxy.service;

import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.model.bizRequest.home.SaveUserInfoBizRequest;
import com.lee.xnxy.model.dto.ResponseResult;

public interface HomeService {
    ResponseResult getUserInfo(Long userId) throws BizException;

    ResponseResult saveUserInfo(SaveUserInfoBizRequest saveUserInfoBizRequest);
}
