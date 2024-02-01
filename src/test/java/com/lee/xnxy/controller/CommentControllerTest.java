package com.lee.xnxy.controller;

import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.request.comment.DeleteCommentRequest;
import com.lee.xnxy.model.request.comment.ListCommentRequest;
import com.lee.xnxy.model.request.comment.SaveCommentRequest;
import com.lee.xnxy.model.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CommentControllerTest {
    @Resource
    private CommentController commentController;

    @Test
    void listComment() {
        ListCommentRequest listCommentRequest = new ListCommentRequest();
        listCommentRequest.setPostId("1");

        ResponseResult responseResult = commentController.listComment(listCommentRequest);
        assert responseResult.getSuccess();
    }

    @Test
    void saveComment() throws SysException {
        SaveCommentRequest saveCommentRequest = new SaveCommentRequest();
        saveCommentRequest.setPostId("-1");
        saveCommentRequest.setContent("测试内容");

        ResponseResult responseResult = commentController.saveComment(saveCommentRequest);
        assert responseResult.getSuccess();
    }

    @Test
    void deleteComment() {
        DeleteCommentRequest deleteCommentRequest = new DeleteCommentRequest();
        deleteCommentRequest.setCommentId("-1");
        ResponseResult responseResult = commentController.deleteComment(deleteCommentRequest);
        assert responseResult.getSuccess();
    }
}