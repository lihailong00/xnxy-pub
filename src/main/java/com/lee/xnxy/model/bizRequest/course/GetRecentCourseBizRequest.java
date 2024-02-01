package com.lee.xnxy.model.bizRequest.course;

import lombok.Data;

@Data
public class GetRecentCourseBizRequest {
    private String jwcUsername;
    private Integer startWeek;
    private Integer startDay;
    private Integer endWeek;
    private Integer endDay;
    /**
     * 开课学期
     */
    private String kkxq;
}
