package com.lee.xnxy.converter.comment;

import com.lee.xnxy.model.bizRequest.comment.DeleteCommentBizRequest;
import com.lee.xnxy.model.request.comment.DeleteCommentRequest;

public class DeleteCommentBizRequestConverter {
    private DeleteCommentBizRequestConverter() {

    }

    public static DeleteCommentBizRequest toDeleteCommentBizRequest(DeleteCommentRequest deleteCommentRequest) {
        if (deleteCommentRequest == null) {
            return new DeleteCommentBizRequest();
        }
        DeleteCommentBizRequest deleteCommentBizRequest = new DeleteCommentBizRequest();
        deleteCommentBizRequest.setCommentId(Long.valueOf(deleteCommentRequest.getCommentId()));
        return deleteCommentBizRequest;
    }
}
