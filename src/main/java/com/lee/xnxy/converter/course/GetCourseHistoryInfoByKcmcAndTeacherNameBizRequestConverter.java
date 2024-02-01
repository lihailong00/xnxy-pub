package com.lee.xnxy.converter.course;

import com.lee.xnxy.model.bizRequest.course.GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest;
import com.lee.xnxy.model.request.course.GetCourseHistoryInfoByKcmcAndTeacherNameRequest;

public class GetCourseHistoryInfoByKcmcAndTeacherNameBizRequestConverter {
    private GetCourseHistoryInfoByKcmcAndTeacherNameBizRequestConverter() {

    }

    public static GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest toGetCourseHistoryInfoByKcmcAndTeacherNameBizRequest(GetCourseHistoryInfoByKcmcAndTeacherNameRequest req) {
        GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest getCourseHistoryInfoByKcmcAndTeacherNameBizRequest = new GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest();
        if (req == null) {
            return getCourseHistoryInfoByKcmcAndTeacherNameBizRequest;
        }
        getCourseHistoryInfoByKcmcAndTeacherNameBizRequest.setKcmc(req.getKcmc());
        getCourseHistoryInfoByKcmcAndTeacherNameBizRequest.setTeacherName(req.getTeacherName());
        return getCourseHistoryInfoByKcmcAndTeacherNameBizRequest;
    }
}
