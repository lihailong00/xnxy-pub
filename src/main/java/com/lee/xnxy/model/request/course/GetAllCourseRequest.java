package com.lee.xnxy.model.request.course;


import com.lee.xnxy.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("GetAllCourseRequest: 分页获取所有课程。")
@Data
public class GetAllCourseRequest {
    @ApiModelProperty("分页时，当前页数")
    @NotNull
    private Integer pageNumber;

    @ApiModelProperty("搜索关键字")
    private String keyword;
}
