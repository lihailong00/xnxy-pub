package com.lee.xnxy.converter.course;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.model.bizRequest.course.GetAllCourseBizRequest;
import com.lee.xnxy.model.request.course.GetAllCourseRequest;
import org.apache.commons.lang3.StringUtils;

public class GetAllCourseBizRequestConverter {
    private GetAllCourseBizRequestConverter() {

    }

    public static GetAllCourseBizRequest toGetAllCourseBizRequest(GetAllCourseRequest getAllCourseRequest) {
        if (getAllCourseRequest == null) {
            return new GetAllCourseBizRequest();
        }
        GetAllCourseBizRequest getAllCourseBizRequest = new GetAllCourseBizRequest();
        getAllCourseBizRequest.setPageNumber(getAllCourseRequest.getPageNumber());
        getAllCourseBizRequest.setPageSize(CommonConstant.PAGE_SIZE);
        String keyword = getKeyWordForSearch(getAllCourseRequest.getKeyword());
        getAllCourseBizRequest.setKeyword(keyword);
        return getAllCourseBizRequest;
    }

    private static String getKeyWordForSearch(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return keyword;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyword.length(); i++) {
            sb.append(keyword.charAt(i));
            sb.append("%");
        }
        return sb.toString();
    }
}
