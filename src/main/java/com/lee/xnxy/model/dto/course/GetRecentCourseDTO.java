package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetRecentCourseDTO {
    private String kcbh;

    private Integer kxh;

    private String kcmc;

    private BigDecimal xf;

    private String kcsx;

    private String xkjd;

    private Integer week;

    private Integer day;

    private Integer startSection;

    private Integer endSection;
}
