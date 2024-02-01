package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetCourseInfoByKcmcAndTeacherNameDTO {
    private String kcmc;

    private String teacherName;

    private BigDecimal xf;

    private Integer zxs;

    private Integer totalStudent;

    private BigDecimal averageScore;

    private Integer fail;

    private Integer pass;

    private Integer intermediate;

    private Integer good;

    private Integer outstanding;
}
