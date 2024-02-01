package com.lee.xnxy.converter.comment;

import com.lee.xnxy.model.bizRequest.comment.ListCommentBizRequest;
import com.lee.xnxy.model.request.comment.ListCommentRequest;

public class ListCommentBizRequestConverter {
    private ListCommentBizRequestConverter() {

    }

    public static ListCommentBizRequest toListCommentBizRequest(ListCommentRequest listCommentRequest) {
        if (listCommentRequest == null) {
            return new ListCommentBizRequest();
        }
        ListCommentBizRequest listCommentBizRequest = new ListCommentBizRequest();
        listCommentBizRequest.setPostId(Long.valueOf(listCommentRequest.getPostId()));
        return listCommentBizRequest;
    }
}
