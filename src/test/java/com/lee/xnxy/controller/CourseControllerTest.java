package com.lee.xnxy.controller;

import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.model.request.course.*;
import com.lee.xnxy.model.dto.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CourseControllerTest {
    @Resource
    private CourseController courseController;

    @Test
    void getCourseByCourseName() {
        GetCourseByCourseNameRequest getCourseByCourseNameRequest = new GetCourseByCourseNameRequest();
        getCourseByCourseNameRequest.setCourseName("线性代数");
        ResponseResult courseByCourseName = courseController.getCourseByCourseName(getCourseByCourseNameRequest, "");
        assert courseByCourseName.getSuccess();
    }

    @Test
    void getCourseByTeacherName() {
        GetCourseByTeacherNameRequest getCourseByTeacherNameRequest = new GetCourseByTeacherNameRequest();
        getCourseByTeacherNameRequest.setTeacherName("孙晋");
        ResponseResult courseByTeacherName = courseController.getCourseByTeacherName(getCourseByTeacherNameRequest, "");
        assert courseByTeacherName.getSuccess();
    }

    @Deprecated
    @Test
    void getCourseInfoByKcbhAndKxh() {
        GetCourseInfoByKcbhAndKxhRequest getCourseInfoByKcbhAndKxhRequest = new GetCourseInfoByKcbhAndKxhRequest();
        getCourseInfoByKcbhAndKxhRequest.setKcbh("111");
        getCourseInfoByKcbhAndKxhRequest.setKxh(1);
        ResponseResult courseInfoByKcbhAndKxh = courseController.getCourseInfoByKcbhAndKxh(getCourseInfoByKcbhAndKxhRequest, "");
        assert courseInfoByKcbhAndKxh.getSuccess();
    }

    @Test
    void getAllCourse() {
        GetAllCourseRequest getAllCourseRequest = new GetAllCourseRequest();
        getAllCourseRequest.setPageNumber(1);
        courseController.getAllCourse(getAllCourseRequest, "");
    }

    @Test
    void getMyCourseTable() throws BizException {
        try {
            courseController.getMyCourseTable("");
        } catch (BizException e) {}
    }

    @Test
    public void getScoreInfo() throws BizException {
        try {
            courseController.getScoreInfo("");
        } catch (BizException e) {}
    }

    @Test
    public void getRecentCourse() throws BizException {
        GetRecentCourseRequest getRecentCourseRequest = new GetRecentCourseRequest();
        getRecentCourseRequest.setStartWeek(1);
        getRecentCourseRequest.setStartDay(1);
        getRecentCourseRequest.setEndWeek(1);
        getRecentCourseRequest.setEndDay(1);
        try {
            courseController.getRecentCourse(getRecentCourseRequest, "");
        } catch (BizException e) {

        }
    }

    @Test
    public void getCourseInfoByKcmcAndTeacherName() {
        GetCourseInfoByKcmcAndTeacherNameRequest getCourseInfoByKcmcAndTeacherNameRequest = new GetCourseInfoByKcmcAndTeacherNameRequest();
        getCourseInfoByKcmcAndTeacherNameRequest.setKcmc("计算机网络");
        getCourseInfoByKcmcAndTeacherNameRequest.setTeacherName("陈清华");
        ResponseResult responseResult = courseController.getCourseInfoByKcmcAndTeacherName(getCourseInfoByKcmcAndTeacherNameRequest, "");
        assert responseResult.getSuccess();
    }
}