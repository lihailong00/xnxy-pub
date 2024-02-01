package com.lee.xnxy.model.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListCommentDTO {
    private String commentId;

    private String userId;

    private String userImage;

    private String username;

    private String content;

    private Integer likeCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 当前用户是否点赞
     */
    private Boolean liked;

    // todo 前端完成帖子是否属于自己
    private Boolean belongToMe;
}
