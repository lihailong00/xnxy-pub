package com.lee.xnxy.controller;

import com.lee.xnxy.aop.DynamicValidate;
import com.lee.xnxy.aop.LogAnnotation;
import com.lee.xnxy.converter.posts.CreatePostsBizRequestConverter;
import com.lee.xnxy.converter.posts.ListPostsBizRequestConverter;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.posts.CreatePostsBizRequest;
import com.lee.xnxy.model.bizRequest.posts.ListPostsBizRequest;
import com.lee.xnxy.model.request.posts.*;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.PostsService;
import com.lee.xnxy.util.IPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 晓龙coding
 */
@RestController
@RequestMapping("/posts")
@Api("发帖模块")
public class PostsController {
    @Resource
    private PostsService postsService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @PostMapping("/create")
    @LogAnnotation(module="帖子", operator="发布")
    @ApiOperation("保存帖子")
    ResponseResult createPosts(@RequestBody @Valid CreatePostsRequest createPostsRequest) throws SysException {
        String ip = IPUtil.getIpAddr(httpServletRequest);
        CreatePostsBizRequest createPostsBizRequest = CreatePostsBizRequestConverter.toCreatePostsBizRequest(createPostsRequest, ip);
        return postsService.savePost(createPostsBizRequest);
    }

    @PostMapping("/list")
    @LogAnnotation(module="帖子", operator="查询")
    @ApiOperation("显示帖子")
    @DynamicValidate
    ResponseResult listPost(@RequestBody @Valid ListPostsRequest listPostsRequest) {
        ListPostsBizRequest listPostsBizRequest = ListPostsBizRequestConverter.toListPostsBizRequest(listPostsRequest);
        return postsService.listPost(listPostsBizRequest);
    }

    @DynamicValidate
    @PostMapping("/delete")
    @LogAnnotation(module="帖子", operator="删除")
    @ApiOperation("删除帖子")
    ResponseResult deletePosts(@RequestBody @Valid DeletePostsRequest deletePostsRequest) {
        Long pId = Long.valueOf(deletePostsRequest.getPostId());
        return postsService.deletePostById(pId);
    }

    @PostMapping("view")
    @ApiOperation("查看帖子时，发送一个异步请求，实现浏览数+1")
    ResponseResult viewPosts(@RequestBody @Valid ViewPostsRequest viewPostsRequest) {
        Long postId = Long.valueOf(viewPostsRequest.getPostId());
        return postsService.viewPosts(postId);
    }
}
