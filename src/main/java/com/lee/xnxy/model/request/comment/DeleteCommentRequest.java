package com.lee.xnxy.model.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("DeleteCommentRequest: 删除评论。")
@Data
public class DeleteCommentRequest {
    @ApiModelProperty("评论id")
    @NotNull
    private String CommentId;
}
