package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetCourseByTeacherNameDTO {
    private String kcbh;
    private Integer kxh;
    private String teacherName;
    private String kcmc;
    private BigDecimal averageScore;
    private Integer totalPeople;
}
