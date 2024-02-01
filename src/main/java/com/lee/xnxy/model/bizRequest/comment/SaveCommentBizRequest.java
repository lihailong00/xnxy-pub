package com.lee.xnxy.model.bizRequest.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaveCommentBizRequest {
    private Long postId;

    private Long userId;

    private String content;

    private LocalDateTime createTime;

    private String ip;

    private Integer hasDeleted;
}
