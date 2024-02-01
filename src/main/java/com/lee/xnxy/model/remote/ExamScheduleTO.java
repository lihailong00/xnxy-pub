package com.lee.xnxy.model.remote;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamScheduleTO {
    private Long id;

    /**
     * 学生学号
     */
    private String jwcUsername;

    /**
     * 考试场次
     */
    private String kscc;

    /**
     * 课程编号
     */
    private String kcbh;

    /**
     * 课程名称
     */
    private String kcmc;

    /**
     * 考试时间
     */
    private String kssj;

    /**
     * 考场
     */
    private String kc;

    /**
     * 座位号
     */
    private String zwh;

    /**
     * 考试学期
     */
    private String term;
}
