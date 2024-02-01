package com.lee.xnxy.model.remote;

import lombok.Data;

@Data
public class CourseAndTimeWithPlaceTO {
    /**
     * 课程名称
     */
    private String kcmc;
    /**
     * 课程编号
     */
    private String kcbh;
    /**
     * 课序号
     */
    private Integer kxh;
    /**
     * 这节课是第几周
     */
    private Integer week;
    /**
     * 这节课是星期几
     */
    private Integer day;
    /**
     * 这节课的开始节
     */
    private Integer startSection;
    /**
     * 这节课的结束节
     */
    private Integer endSection;
    /**
     * 上课地点
     */
    private String place;
}
