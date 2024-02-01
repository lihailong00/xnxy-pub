package com.lee.xnxy.model.request.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("GetCourseByTeacherNameRequest: 通过老师名获取名下的所有课程信息。")
@Data
public class GetCourseByTeacherNameRequest {
    @ApiModelProperty("老师名")
    @NotBlank
    private String teacherName;
}
