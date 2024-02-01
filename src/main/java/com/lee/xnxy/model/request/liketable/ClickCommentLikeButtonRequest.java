package com.lee.xnxy.model.request.liketable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("ClickCommentLikeButtonRequest: 点击点赞按钮。可能会点赞，也可能取消赞。")
@Data
public class ClickCommentLikeButtonRequest {
    @ApiModelProperty("点赞的评论id")
    @NotNull
    private String commentId;

    @ApiModelProperty("谁点赞(当前用户)")
    @NotNull
    private String likeUserId;
}
