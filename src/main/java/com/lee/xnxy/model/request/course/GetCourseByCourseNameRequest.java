package com.lee.xnxy.model.request.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("GetCourseByCourseNameRequest: 通过课程名获取课程详细信息。")
public class GetCourseByCourseNameRequest {
    @ApiModelProperty(value = "课程名", required = true)
    @NotBlank
    private String courseName;
}
