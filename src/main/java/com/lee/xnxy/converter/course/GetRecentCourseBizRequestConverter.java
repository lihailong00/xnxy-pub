package com.lee.xnxy.converter.course;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.bizRequest.course.GetRecentCourseBizRequest;
import com.lee.xnxy.model.request.course.GetRecentCourseRequest;

public class GetRecentCourseBizRequestConverter {
    private GetRecentCourseBizRequestConverter() {

    }

    public static GetRecentCourseBizRequest toGetRecentCourseBizRequest(String jwcUsername, GetRecentCourseRequest getRecentCourseRequest) {
        GetRecentCourseBizRequest getRecentCourseBizRequest = new GetRecentCourseBizRequest();
        getRecentCourseBizRequest.setJwcUsername(jwcUsername);
        getRecentCourseBizRequest.setStartWeek(getRecentCourseRequest.getStartWeek());
        getRecentCourseBizRequest.setStartDay(getRecentCourseRequest.getStartDay());
        getRecentCourseBizRequest.setEndWeek(getRecentCourseRequest.getEndWeek());
        getRecentCourseBizRequest.setEndDay(getRecentCourseRequest.getEndDay());
        getRecentCourseBizRequest.setKkxq(CommonConstant.NOW_TERM);
        return getRecentCourseBizRequest;
    }
}
