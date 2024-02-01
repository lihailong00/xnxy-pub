package com.lee.xnxy.converter.course;

import com.lee.xnxy.model.bizRequest.course.GetCourseInfoByKcbhAndKxhBizRequest;
import com.lee.xnxy.model.request.course.GetCourseInfoByKcbhAndKxhRequest;

@Deprecated
public class GetCourseInfoByKcbhAndKxhBizRequestConverter {
    private GetCourseInfoByKcbhAndKxhBizRequestConverter() {

    }

    public static GetCourseInfoByKcbhAndKxhBizRequest toGetCourseInfoByKcbhAndKxhBizRequest(GetCourseInfoByKcbhAndKxhRequest getCourseInfoByKcbhAndKxhRequest) {
        if (getCourseInfoByKcbhAndKxhRequest == null) {
            return new GetCourseInfoByKcbhAndKxhBizRequest();
        }
        GetCourseInfoByKcbhAndKxhBizRequest getCourseInfoByKcbhAndKxhBizRequest = new GetCourseInfoByKcbhAndKxhBizRequest();
        getCourseInfoByKcbhAndKxhBizRequest.setKcbh(getCourseInfoByKcbhAndKxhRequest.getKcbh());
        getCourseInfoByKcbhAndKxhBizRequest.setKxh(getCourseInfoByKcbhAndKxhRequest.getKxh());
        return getCourseInfoByKcbhAndKxhBizRequest;
    }
}
