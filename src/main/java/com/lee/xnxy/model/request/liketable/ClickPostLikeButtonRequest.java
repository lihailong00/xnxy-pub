package com.lee.xnxy.model.request.liketable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("ClickPostLikeButtonRequest: 给帖子点赞。有可能点赞，有可能取消赞。")
@Data
public class ClickPostLikeButtonRequest {
    @ApiModelProperty("帖子id")
    @NotNull
    private String postId;

    @ApiModelProperty("谁点赞(当前用户)")
    @NotNull
    private String likeUserId;
}
