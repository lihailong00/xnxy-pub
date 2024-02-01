package com.lee.xnxy.model.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("ListCommentRequest: 展示评论的请求。根据文章id搜索文章的评论")
public class ListCommentRequest {
    /**
     * 帖子postId
     */
    @NotNull
    @ApiModelProperty("当前文章的id")
    private String postId;
}
