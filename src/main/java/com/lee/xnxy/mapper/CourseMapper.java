package com.lee.xnxy.mapper;

import com.lee.xnxy.model.bizRequest.course.*;
import com.lee.xnxy.model.dto.course.*;
import com.lee.xnxy.model.dao.course.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 20882
* @description 针对表【xnxy_course】的数据库操作Mapper
* @createDate 2023-10-14 20:45:59
* @Entity generator.domain.XnxyCourse
*/
public interface CourseMapper extends BaseMapper<Course> {
    List<GetCourseByCourseNameDTO> getCourseByCourseName(String courseName);

    List<GetCourseByTeacherNameDTO> getCourseByTeacherName(String teacherName);

    List<GetCourseInfoByKcbhAndKxhDTO> getCourseInfoByKcbhAndKxh(GetCourseInfoByKcbhAndKxhBizRequest getCourseInfoByKcbhAndKxhBizRequest);

    List<GetAllCourseDTO> getAllCourse(GetAllCourseBizRequest getAllCourseBizRequest);

    List<GetCourseTableDTO> getCourseTable(String jwcUsername, String nowTerm);

    List<GetRecentCourseDTO> getRecentCourse(GetRecentCourseBizRequest getRecentCourseBizRequest);

    List<GetCourseInfoByKcmcAndTeacherNameDTO> getCourseInfoByKcmcAndTeacherName(GetCourseInfoByKcmcAndTeacherNameBizRequest getCourseInfoByKcmcAndTeacherNameBizRequest);

    List<GetCourseHistoryInfoByKcmcAndTeacherNameDTO> getCourseHistoryInfoByKcmcAndTeacherName(GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest getCourseHistoryInfoByKcmcAndTeacherNameBizRequest);
}




