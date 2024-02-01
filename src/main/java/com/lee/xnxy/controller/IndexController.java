package com.lee.xnxy.controller;

import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.AdminTaskService;
import com.lee.xnxy.service.IndexSwiperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("index")
@Api("首页模块")
public class IndexController {
    @Resource
    private IndexSwiperService indexSwiperService;

    @Resource
    private AdminTaskService adminTaskService;

    @PostMapping("swiper")
    @ApiOperation("获取首页轮播图url")
    public ResponseResult getIndexSwiper() {
        return indexSwiperService.getIndexSwiper();
    }


    @PostMapping("list-todo")
    @ApiOperation("首页开发者todo项")
    public ResponseResult listAdminTodo() {
        return adminTaskService.listAdminTodo();
    }
}
