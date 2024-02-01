package com.lee.xnxy.converter.course;

import com.lee.xnxy.model.bizRequest.course.GetMyCourseTableBizRequest;

public class GetMyCourseTableBizRequestConverter {
    private GetMyCourseTableBizRequestConverter() {

    }

    public static GetMyCourseTableBizRequest toGetMyCourseTableBizRequest(String jwcUsername, String nowTerm) {
        GetMyCourseTableBizRequest getMyCourseTableBizRequest = new GetMyCourseTableBizRequest();
        getMyCourseTableBizRequest.setJwcUsername(jwcUsername);
        getMyCourseTableBizRequest.setNowTerm(nowTerm);
        return getMyCourseTableBizRequest;
    }
}
