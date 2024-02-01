package com.lee.xnxy.model.request.liketable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("HasClickButtonRequest: 判断当前用户是否给文章点赞")
@Data
public class HasClickButtonRequest {
    @ApiModelProperty("文章id")
    @NotNull(message = "文章id不能为空")
    private String postId;
}
