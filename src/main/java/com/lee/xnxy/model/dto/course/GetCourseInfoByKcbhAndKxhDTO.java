package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.math.BigDecimal;

@Deprecated
@Data
public class GetCourseInfoByKcbhAndKxhDTO {
    private String kcbh;

    private Integer kxh;

    private String kcmc;

    private BigDecimal xf;

    private Integer zxs;

    private String khfs;

    private String kcsx;

    private String kcxz;

    private String teacherName;

    private Integer totalStudent;

    private BigDecimal averageScore;

    private Integer fail;

    private Integer pass;

    private Integer intermediate;

    private Integer good;

    private Integer outstanding;
}
