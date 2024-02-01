package com.lee.xnxy.converter.course;

import com.lee.xnxy.model.bizRequest.course.GetCourseByCourseNameBizRequest;
import com.lee.xnxy.model.request.course.GetCourseByCourseNameRequest;
import lombok.Data;

@Data
public class GetCourseByCourseNameBizRequestConverter {
    private GetCourseByCourseNameBizRequestConverter() {

    }

    public static GetCourseByCourseNameBizRequest toGetCourseByCourseNameBizRequest(GetCourseByCourseNameRequest getCourseByCourseNameRequest) {
        if (getCourseByCourseNameRequest == null) {
            return new GetCourseByCourseNameBizRequest();
        }
        GetCourseByCourseNameBizRequest getCourseByCourseNameBizRequest = new GetCourseByCourseNameBizRequest();
        getCourseByCourseNameBizRequest.setCourseName(getCourseByCourseNameRequest.getCourseName());
        return getCourseByCourseNameBizRequest;
    }
}
