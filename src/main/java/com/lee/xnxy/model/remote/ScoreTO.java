package com.lee.xnxy.model.remote;

import lombok.Data;

@Data
public class ScoreTO {
    /**
     * 序号
     */
    private Integer xh;
    /**
     * 开课学期
     */
    private String kkxq;
    /**
     * 课程编号
     */
    private String kcbh;
    /**
     * 课程名称
     */
    private String kcmc;
    /**
     * 成绩
     */
    private String cj;
    /**
     * 成绩标识
     */
    private String cjbs;
    /**
     * 学分
     */
    private Double xf;
    /**
     * 总学时
     */
    private Integer zxs;
    /**
     * 考核方式
     */
    private String khfs;
    /**
     * 课程属性
     */
    private String kcsx;
    /**
     * 课程性质
     */
    private String kcxz;
    /**
     * 学号
     */
    private String jwcUsername;
    /**
     * 成绩中文(优秀/良好/中等/及格/不及格)
     */
    private String cjzw;
}
