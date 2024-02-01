package com.lee.xnxy.model.request.posts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@ApiModel("CreatePostsRequest: 创建帖子。")
@Data
public class CreatePostsRequest {
    /**
     * 文章标题
     */
    @ApiModelProperty("文章标题")
    @Length(min = 4, max = 10, message = "文章标题请在4~100字之间")
    private String title;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    @Length(min = 4, max = 2000, message = "文章内容请在4~2000字之间")
    private String content;
}
