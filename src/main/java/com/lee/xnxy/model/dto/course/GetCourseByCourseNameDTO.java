package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetCourseByCourseNameDTO {
    private String kcbh;
    private Integer kxh;
    private String kcmc;
    private String teacherName;
    private BigDecimal averageScore;
    private Integer totalPeople;
}
