package com.lee.xnxy.model.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ApiModel("SaveCommentRequest:保存评论请求。需要指定当前评论所属的文章id。")
@Data
public class SaveCommentRequest {
    @ApiModelProperty("评论所属文章id")
    @NotNull
    private String postId;

    @ApiModelProperty("评论内容")
    @Length(min = 1, max = 2000, message = "评论内容最少1个字，最多2000个字")
    private String content;
}
