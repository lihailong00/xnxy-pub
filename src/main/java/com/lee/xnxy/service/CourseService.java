package com.lee.xnxy.service;

import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.bizRequest.course.*;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.model.remote.FirstLoginJwcRespTO;

public interface CourseService {
    ResponseResult getCourseByCourseName(GetCourseByCourseNameBizRequest getCourseByCourseNameRequest);

    ResponseResult getCourseByTeacherName(GetCourseByTeacherNameBizRequest getCourseByTeacherNameBizRequest);

    @Deprecated
    ResponseResult getCourseInfoByKcbhAndKxh(GetCourseInfoByKcbhAndKxhBizRequest getCourseInfoByKcbhAndKxhBizRequest);

    ResponseResult getAllCourse(GetAllCourseBizRequest getAllCourseBizRequest);

    ResponseResult getCourseTable(GetMyCourseTableBizRequest getMyCourseTableBizRequest);

    ResponseResult getScoreInfo(String jwcUsername);

    ResponseResult getRecentCourse(GetRecentCourseBizRequest getRecentCourseBizRequest);

    ResponseResult savePersonJwcInfo(FirstLoginJwcRespTO firstLoginJwcRespTO) throws SysException;

    ResponseResult getCourseInfoByKcmcAndTeacherName(GetCourseInfoByKcmcAndTeacherNameBizRequest getCourseInfoByKcmcAndTeacherNameBizRequest);

    ResponseResult getCourseHistoryInfoByKcmcAndTeacherName(GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest getCourseHistoryInfoByKcmcAndTeacherNameBizRequest);

    ResponseResult getRecentExam() throws BizException;
}
