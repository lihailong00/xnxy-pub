package com.lee.xnxy.controller;

import com.lee.xnxy.model.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class IndexControllerTest {
    @Resource
    private IndexController indexController;

    @Test
    public void getIndexSwiper() {
        ResponseResult responseResult = indexController.getIndexSwiper();
        assert responseResult.getSuccess();
    }

    @Test
    public void listAdminTodo() {
        ResponseResult responseResult = indexController.listAdminTodo();
        assert responseResult.getSuccess();
    }
}
