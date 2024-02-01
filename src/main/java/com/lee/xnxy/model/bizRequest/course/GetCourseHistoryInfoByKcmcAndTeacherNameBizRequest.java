package com.lee.xnxy.model.bizRequest.course;

import lombok.Data;

@Data
public class GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest {
    private String kcmc;

    private String teacherName;
}
