package com.lee.xnxy.controller;


import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.model.request.home.SaveUserInfoRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class HomeControllerTest {
    @Resource
    private HomeController homeController;
    @Test
    public void getUserInfo() throws BizException {
        try {
            ResponseResult userInfo = homeController.getUserInfo("");
        } catch (BizException e) {}
    }

    @Test
    public void saveUserInfo() {
        SaveUserInfoRequest saveUserInfoRequest = new SaveUserInfoRequest();
        ResponseResult responseResult = homeController.saveUserInfo(saveUserInfoRequest, "");
        assert responseResult.getSuccess();
    }
}
