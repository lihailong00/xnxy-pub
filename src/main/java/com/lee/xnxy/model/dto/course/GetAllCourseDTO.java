package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetAllCourseDTO {
    private String kcmc;

    private String kcbh;

    private Integer kxh;

    private String teacherName;

    private BigDecimal xf;

    private String kcsx;
}
