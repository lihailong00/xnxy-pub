package com.lee.xnxy.model.request.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Deprecated
@ApiModel("GetCourseInfoByKcbhAndKxhRequest: 通过课程编号和课序号获取一门课程的详情。")
@Data
public class GetCourseInfoByKcbhAndKxhRequest {
    @ApiModelProperty("课程编号")
    @NotBlank
    private String kcbh;

    @ApiModelProperty("课序号")
    @NotNull
    private Integer kxh;
}
