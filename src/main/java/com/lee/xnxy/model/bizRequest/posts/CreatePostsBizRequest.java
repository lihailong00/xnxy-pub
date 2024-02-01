package com.lee.xnxy.model.bizRequest.posts;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePostsBizRequest {
    /**
     * 发帖者id
     */
    private Long uId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章发布时间
     */
    private LocalDateTime createTime;

    /**
     * 文章是否被删除，0表示未删除，1表示删除
     */
    private Integer hasDeleted;

    /**
     * 发布IP
     */
    private String ip;
}
