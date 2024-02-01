package com.lee.xnxy.converter.course;

import com.lee.xnxy.model.bizRequest.course.GetCourseByTeacherNameBizRequest;
import com.lee.xnxy.model.request.course.GetCourseByTeacherNameRequest;

public class GetCourseByTeacherNameBizRequestConverter {
    private GetCourseByTeacherNameBizRequestConverter() {

    }

    public static GetCourseByTeacherNameBizRequest toGetCourseByTeacherNameBizRequest(GetCourseByTeacherNameRequest getCourseByTeacherNameRequest) {
        if (getCourseByTeacherNameRequest == null) {
            return new GetCourseByTeacherNameBizRequest();
        }
        GetCourseByTeacherNameBizRequest getCourseByTeacherNameBizRequest = new GetCourseByTeacherNameBizRequest();
        getCourseByTeacherNameBizRequest.setTeacherName(getCourseByTeacherNameRequest.getTeacherName());
        return getCourseByTeacherNameBizRequest;
    }
}
