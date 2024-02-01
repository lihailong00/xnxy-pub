package com.lee.xnxy.model.request.liketable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("GetPostLikeCountRequest: 获取当前文章的点赞数")
@Data
public class GetPostLikeCountRequest {
    @ApiModelProperty("帖子id")
    String postId;
}
