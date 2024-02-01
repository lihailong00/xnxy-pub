package com.lee.xnxy.model.remote;

import lombok.Data;

@Data
public class JwcPersonInfoTO {
    /**
     * 学号
     */
    private String jwcUsername;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 教务处密码
     */
    private String password;
    /**
     * 性别：女或男
     */
    private String gender;
    /**
     * 学院
     */
    private String school;
    /**
     * 专业
     */
    private String major;
}
