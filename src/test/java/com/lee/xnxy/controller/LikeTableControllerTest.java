package com.lee.xnxy.controller;

import com.lee.xnxy.model.request.liketable.ClickCommentLikeButtonRequest;
import com.lee.xnxy.model.request.liketable.ClickPostLikeButtonRequest;
import com.lee.xnxy.model.request.liketable.GetPostLikeCountRequest;
import com.lee.xnxy.model.request.liketable.HasClickButtonRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class LikeTableControllerTest {
    @Resource
    private LikeTableController likeTableController;

    @Test
    public void hasClickButton() {
        HasClickButtonRequest hasClickButtonRequest = new HasClickButtonRequest();
        hasClickButtonRequest.setPostId("-1");
        ResponseResult responseResult = likeTableController.hasClickButton(hasClickButtonRequest);
        assert responseResult.getSuccess();
    }

    @Test
    public void getPostLikeCount() {
        GetPostLikeCountRequest getPostLikeCountRequest = new GetPostLikeCountRequest();
        getPostLikeCountRequest.setPostId("1");
        ResponseResult responseResult = likeTableController.getPostLikeCount(getPostLikeCountRequest);
        assert responseResult.getSuccess();
    }

    @Test
    public void clickPostLikeButton() {
        ClickPostLikeButtonRequest clickPostLikeButtonRequest = new ClickPostLikeButtonRequest();
        clickPostLikeButtonRequest.setPostId("1");
        clickPostLikeButtonRequest.setLikeUserId("1");
        ResponseResult responseResult = likeTableController.clickPostLikeButton(clickPostLikeButtonRequest);
        assert responseResult.getSuccess();
    }

    @Test
    public void clickCommentLikeButton() {
        ClickCommentLikeButtonRequest clickCommentLikeButtonRequest = new ClickCommentLikeButtonRequest();
        clickCommentLikeButtonRequest.setLikeUserId("1");
        clickCommentLikeButtonRequest.setCommentId("1");
        ResponseResult responseResult = likeTableController.clickCommentLikeButton(clickCommentLikeButtonRequest);
        assert responseResult.getSuccess();
    }
}
