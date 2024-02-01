package com.lee.xnxy.model.request.posts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("DeletePostsRequest: 删除帖子。")
@Data
public class DeletePostsRequest {
    @ApiModelProperty("帖子id")
    @NotEmpty
    private String postId;
}
