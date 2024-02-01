package com.lee.xnxy.model.request.course;

import lombok.Data;

@Data
public class GetCourseHistoryInfoByKcmcAndTeacherNameRequest {
    private String kcmc;

    private String teacherName;
}
