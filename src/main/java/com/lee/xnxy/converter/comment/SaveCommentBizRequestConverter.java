package com.lee.xnxy.converter.comment;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.bizRequest.comment.SaveCommentBizRequest;
import com.lee.xnxy.model.request.comment.SaveCommentRequest;

import java.time.LocalDateTime;

public class SaveCommentBizRequestConverter {
    private SaveCommentBizRequestConverter() {

    }

    public static SaveCommentBizRequest toSaveCommentBizRequest(SaveCommentRequest saveCommentRequest, Long userId, String ip) {
        SaveCommentBizRequest saveCommentBizRequest = new SaveCommentBizRequest();
        saveCommentBizRequest.setUserId(userId);
        saveCommentBizRequest.setPostId(Long.valueOf(saveCommentRequest.getPostId()));
        saveCommentBizRequest.setContent(saveCommentRequest.getContent());
        saveCommentBizRequest.setCreateTime(LocalDateTime.now());
        saveCommentBizRequest.setIp(ip);
        saveCommentBizRequest.setHasDeleted(CommonConstant.NOT_DELETE);
        return saveCommentBizRequest;
    }
}
