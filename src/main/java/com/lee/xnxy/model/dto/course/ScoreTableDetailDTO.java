package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScoreTableDetailDTO {
    private String kcbh;

    private String kcmc;

    private BigDecimal cj;

    private String cjbs;

    private BigDecimal xf;

    private Integer zxs;

    private String khfs;

    private String kcsx;

    private String kcxz;

    private String cjzw;

    private BigDecimal gpa;
}
