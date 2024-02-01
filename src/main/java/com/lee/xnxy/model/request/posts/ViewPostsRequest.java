package com.lee.xnxy.model.request.posts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("ViewPostsRequest: 用户打开帖子时，发送一个请求，用于浏览量+1。")
@Data
public class ViewPostsRequest {
    @ApiModelProperty("帖子id")
    @NotEmpty
    private String postId;
}
