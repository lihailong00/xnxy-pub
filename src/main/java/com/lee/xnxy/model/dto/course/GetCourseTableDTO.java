package com.lee.xnxy.model.dto.course;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetCourseTableDTO {
    /**
     * 课程编号
     */
    private String kcbh;
    /**
     * 课序号
     */
    private Integer kxh;
    /**
     * 课程名称
     */
    private String kcmc;
    /**
     * 学分
     */
    private BigDecimal xf;
    /**
     * 课程属性
     */
    private String kcsx;
    /**
     * 选课阶段
     */
    private String xkjd;
    /**
     * 老师名称
     */
    private String teacherName;
    /**
     * 周次
     */
    private Integer week;
    /**
     * 星期几
     */
    private Integer day;
    /**
     * 第几节开始
     */
    private Integer startSection;
    /**
     * 第几节结束
     */
    private Integer endSection;
    /**
     * 上课地点
     */
    private String place;
}
