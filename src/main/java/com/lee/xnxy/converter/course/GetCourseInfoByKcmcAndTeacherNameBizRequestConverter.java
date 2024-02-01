package com.lee.xnxy.converter.course;

import com.lee.xnxy.model.bizRequest.course.GetCourseInfoByKcmcAndTeacherNameBizRequest;
import com.lee.xnxy.model.request.course.GetCourseInfoByKcmcAndTeacherNameRequest;

public class GetCourseInfoByKcmcAndTeacherNameBizRequestConverter {
    private GetCourseInfoByKcmcAndTeacherNameBizRequestConverter() {

    }

    public static GetCourseInfoByKcmcAndTeacherNameBizRequest toGetCourseInfoByKcmcAndTeacherNameBizRequest(GetCourseInfoByKcmcAndTeacherNameRequest getCourseInfoByKcmcAndTeacherNameRequest) {
        GetCourseInfoByKcmcAndTeacherNameBizRequest getCourseInfoByKcmcAndTeacherNameBizRequest = new GetCourseInfoByKcmcAndTeacherNameBizRequest();
        if (getCourseInfoByKcmcAndTeacherNameRequest == null) {
            return getCourseInfoByKcmcAndTeacherNameBizRequest;
        }
        getCourseInfoByKcmcAndTeacherNameBizRequest.setKcmc(getCourseInfoByKcmcAndTeacherNameRequest.getKcmc());
        getCourseInfoByKcmcAndTeacherNameBizRequest.setTeacherName(getCourseInfoByKcmcAndTeacherNameRequest.getTeacherName());
        return getCourseInfoByKcmcAndTeacherNameBizRequest;
    }
}
