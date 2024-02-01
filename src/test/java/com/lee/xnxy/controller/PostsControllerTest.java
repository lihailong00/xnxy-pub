package com.lee.xnxy.controller;

import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.request.posts.CreatePostsRequest;
import com.lee.xnxy.model.request.posts.DeletePostsRequest;
import com.lee.xnxy.model.request.posts.ListPostsRequest;
import com.lee.xnxy.model.request.posts.ViewPostsRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PostsControllerTest {
    @Resource
    private PostsController postsController;

    @Test
    void createPosts() throws SysException {
        CreatePostsRequest createPostsRequest = new CreatePostsRequest();
        createPostsRequest.setTitle("系统测试标题");
        createPostsRequest.setContent("系统测试内容");
        ResponseResult responseResult = postsController.createPosts(createPostsRequest);
        assert responseResult.getSuccess();
    }

    @Test
    void listPost() {
        ListPostsRequest listPostsRequest = new ListPostsRequest();
        listPostsRequest.setUserId("1");
        listPostsRequest.setPageNumber(1);
        listPostsRequest.setKeyword("");
        ResponseResult responseResult = postsController.listPost(listPostsRequest);
        assert responseResult.getCode() == 200;
    }

    @Test
    void deletePosts() {
        DeletePostsRequest deletePostsRequest = new DeletePostsRequest();
        deletePostsRequest.setPostId("-1");
        ResponseResult responseResult = postsController.deletePosts(deletePostsRequest);
        assert responseResult.getCode() == 200;
    }

    @Test
    void viewPosts() {
        ViewPostsRequest viewPostsRequest = new ViewPostsRequest();
        viewPostsRequest.setPostId("-1");
        ResponseResult responseResult = postsController.viewPosts(viewPostsRequest);
        assert responseResult.getSuccess();
    }
}
