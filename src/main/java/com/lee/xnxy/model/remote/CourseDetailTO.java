package com.lee.xnxy.model.remote;

import lombok.Data;

@Data
public class CourseDetailTO {
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
    private Double xf;
    /**
     * 课程属性
     */
    private String kcsx;
    /**
     * 选课阶段
     */
    private String xkjd;
    /**
     * 开课学期
     */
    private String kkxq;
}
