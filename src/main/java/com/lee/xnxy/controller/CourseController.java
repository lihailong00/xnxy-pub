package com.lee.xnxy.controller;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.converter.course.*;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.model.bizRequest.course.*;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.request.course.*;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.service.CourseService;
import com.lee.xnxy.service.UserService;
import com.lee.xnxy.util.UserContextDTOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("course")
@Api(value = "课程&成绩模块")
public class CourseController {
    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;

    @PostMapping("get-course-info-by-course-name")
    @ApiOperation("通过课程名，获取课程详细信息")
    public ResponseResult getCourseByCourseName(@RequestBody @Valid GetCourseByCourseNameRequest getCourseByCourseNameRequest, @RequestHeader("token") String token) {
        GetCourseByCourseNameBizRequest getCourseByCourseNameBizRequest = GetCourseByCourseNameBizRequestConverter.toGetCourseByCourseNameBizRequest(getCourseByCourseNameRequest);
        return courseService.getCourseByCourseName(getCourseByCourseNameBizRequest);
    }

    @PostMapping("get-course-info-ny-teacher-name")
    @ApiOperation("通过老师名称获取课程详情")
    public ResponseResult getCourseByTeacherName(@RequestBody @Valid GetCourseByTeacherNameRequest getCourseByTeacherNameRequest, @RequestHeader("token") String token) {
        GetCourseByTeacherNameBizRequest getCourseByTeacherNameBizRequest = GetCourseByTeacherNameBizRequestConverter.toGetCourseByTeacherNameBizRequest(getCourseByTeacherNameRequest);
        return courseService.getCourseByTeacherName(getCourseByTeacherNameBizRequest);
    }

    @PostMapping("get-score-info-by-kcmc-and-teachername")
    @ApiOperation("通过课程名称和老师名获取课程详情")
    public ResponseResult getCourseInfoByKcmcAndTeacherName(@RequestBody @Valid GetCourseInfoByKcmcAndTeacherNameRequest getCourseInfoByKcmcAndTeacherNameRequest, @RequestHeader("token") String token) {
        GetCourseInfoByKcmcAndTeacherNameBizRequest getCourseInfoByKcmcAndTeacherNameBizRequest = GetCourseInfoByKcmcAndTeacherNameBizRequestConverter.toGetCourseInfoByKcmcAndTeacherNameBizRequest(getCourseInfoByKcmcAndTeacherNameRequest);
        return courseService.getCourseInfoByKcmcAndTeacherName(getCourseInfoByKcmcAndTeacherNameBizRequest);
    }

    // lhl todo
    public ResponseResult getCourseHistoryInfoByKcmcAndTeacherName(@RequestBody @Valid GetCourseHistoryInfoByKcmcAndTeacherNameRequest getCourseHistoryInfoByKcmcAndTeacherNameRequest, @RequestHeader("token") String token) {
        GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest getCourseHistoryInfoByKcmcAndTeacherNameBizRequest = GetCourseHistoryInfoByKcmcAndTeacherNameBizRequestConverter.toGetCourseHistoryInfoByKcmcAndTeacherNameBizRequest(getCourseHistoryInfoByKcmcAndTeacherNameRequest);
        return courseService.getCourseHistoryInfoByKcmcAndTeacherName(getCourseHistoryInfoByKcmcAndTeacherNameBizRequest);
    }

    @Deprecated
    @PostMapping("get-course-info-by-kcbh-and-kxh")
    @ApiOperation("通过课程编号和课程查询课程详情")
    public ResponseResult getCourseInfoByKcbhAndKxh(@RequestBody @Valid GetCourseInfoByKcbhAndKxhRequest getCourseInfoByKcbhAndKxhRequest, @RequestHeader("token") String token) {
        GetCourseInfoByKcbhAndKxhBizRequest getCourseInfoByKcbhAndKxhBizRequest = GetCourseInfoByKcbhAndKxhBizRequestConverter.toGetCourseInfoByKcbhAndKxhBizRequest(getCourseInfoByKcbhAndKxhRequest);
        return courseService.getCourseInfoByKcbhAndKxh(getCourseInfoByKcbhAndKxhBizRequest);
    }

    @PostMapping("get-all-course")
    @ApiOperation("获取所有课程信息，按名字升序排序")
    public ResponseResult getAllCourse(@RequestBody @Valid GetAllCourseRequest getAllCourseRequest, @RequestHeader("token") String token) {
        GetAllCourseBizRequest getAllCourseBizRequest = GetAllCourseBizRequestConverter.toGetAllCourseBizRequest(getAllCourseRequest);
        return courseService.getAllCourse(getAllCourseBizRequest);
    }

    @PostMapping("get-my-course-table")
    @ApiOperation("获取个人课程信息，按照week、day、start_section升序")
    public ResponseResult getMyCourseTable(@RequestHeader("token") String token) throws BizException {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        String jwcUsername = userService.getJwcUsernameByUserId(userId);
        GetMyCourseTableBizRequest getMyCourseTableBizRequest = GetMyCourseTableBizRequestConverter.toGetMyCourseTableBizRequest(jwcUsername, CommonConstant.NOW_TERM);
        return courseService.getCourseTable(getMyCourseTableBizRequest);
    }

    @PostMapping("get-score-info")
    @ApiOperation("获取个人成绩记录")  // todo 语义不太正确
    public ResponseResult getScoreInfo(@RequestHeader("token") String token) throws BizException {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        String jwcUsername = userService.getJwcUsernameByUserId(userId);
        return courseService.getScoreInfo(jwcUsername);
    }

    @PostMapping("get-recent-course")
    @ApiOperation("获取个人近期(今天、明天)的课表")
    public ResponseResult getRecentCourse(@RequestBody @Valid GetRecentCourseRequest getRecentCourseRequest, @RequestHeader("token") String token) throws BizException {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        String jwcUsername = userService.getJwcUsernameByUserId(userId);
        GetRecentCourseBizRequest getRecentCourseBizRequest = GetRecentCourseBizRequestConverter.toGetRecentCourseBizRequest(jwcUsername, getRecentCourseRequest);
        return courseService.getRecentCourse(getRecentCourseBizRequest);
    }

    @PostMapping("get-recent-exam")
    @ApiOperation("获取个人近期考试")
    public ResponseResult getRecentExam() throws BizException {
        return courseService.getRecentExam();
    }
}
