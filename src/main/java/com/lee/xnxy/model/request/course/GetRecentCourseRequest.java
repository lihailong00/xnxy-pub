package com.lee.xnxy.model.request.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("GetRecentCourseRequest: 获取最近指定时间段的课程。可以跨周。")
@Data
public class GetRecentCourseRequest {
    @ApiModelProperty("开始周")
    @NotNull
    private Integer startWeek;

    @ApiModelProperty("开始日")
    @NotNull
    private Integer startDay;

    @ApiModelProperty("结束周")
    @NotNull
    private Integer endWeek;

    @ApiModelProperty("结束日")
    @NotNull
    private Integer endDay;
}
