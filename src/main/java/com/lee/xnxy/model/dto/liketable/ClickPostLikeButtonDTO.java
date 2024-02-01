package com.lee.xnxy.model.dto.liketable;

import lombok.Data;

@Data
public class ClickPostLikeButtonDTO {
    private Integer likeCount;
    /**
     * 当前用户是否点赞
     */
    private Boolean liked;
}
