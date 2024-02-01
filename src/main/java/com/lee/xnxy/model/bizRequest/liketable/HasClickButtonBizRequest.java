package com.lee.xnxy.model.bizRequest.liketable;

import lombok.Data;


@Data
public class HasClickButtonBizRequest {
    private Long likeUserId;

    private Long postId;
}
