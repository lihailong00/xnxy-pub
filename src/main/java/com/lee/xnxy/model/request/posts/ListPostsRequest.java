package com.lee.xnxy.model.request.posts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ApiModel("ListPostsRequest: 分页显示帖子")
@Data
public class ListPostsRequest {
    @ApiModelProperty("搜索帖子时的关键字，有时候为空，表示不使用搜索功能。")
    @Length(min = 0, max = 20)
    private String keyword;

    @ApiModelProperty("当前页数")
    @NotNull
    private Integer pageNumber;

    @ApiModelProperty("帖子所属者。查询个人帖子时需要。")
    private String userId;
}
