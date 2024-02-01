package com.lee.xnxy.model.bizRequest.posts;

import lombok.Data;

@Data
public class ListPostsBizRequest {
    private String keyword;
    private Integer pageNumber;
    private Integer pageSize;
    /**
     * 帖子所属者
     */
    private Long userId;
}
