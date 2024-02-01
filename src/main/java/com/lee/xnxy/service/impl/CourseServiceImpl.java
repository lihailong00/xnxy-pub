package com.lee.xnxy.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.mapper.*;
import com.lee.xnxy.model.bizRequest.course.*;
import com.lee.xnxy.model.dao.course.*;
import com.lee.xnxy.model.dao.home.JwcPersonInfo;
import com.lee.xnxy.model.dao.home.User;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.model.dto.course.*;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.model.remote.*;
import com.lee.xnxy.service.CourseService;
import com.lee.xnxy.service.UserService;
import com.lee.xnxy.util.UserContextDTOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private UserService userService;

    @Resource
    private UserAndCourseMapper userAndCourseMapper;

    @Resource
    private CourseAndTeacherMapper courseAndTeacherMapper;

    @Resource
    private CourseAndTimeWithPlaceMapper courseAndTimeWithPlaceMapper;

    @Resource
    private JwcPersonInfoMapper jwcPersonInfoMapper;

    @Resource
    private ExamScheduleMapper examScheduleMapper;


    @Value("${currentTermStartDate}")
    private String currentTermStartDate;

    public ResponseResult getCourseByCourseName(GetCourseByCourseNameBizRequest getCourseByCourseNameBizRequest) {
        List<GetCourseByCourseNameDTO> courseByCourseName = courseMapper.getCourseByCourseName(getCourseByCourseNameBizRequest.getCourseName());
        return ResponseResult.success(courseByCourseName);
    }

    @Override
    public ResponseResult getCourseByTeacherName(GetCourseByTeacherNameBizRequest getCourseByTeacherNameBizRequest) {
        List<GetCourseByTeacherNameDTO> courseInfoList = courseMapper.getCourseByTeacherName(getCourseByTeacherNameBizRequest.getTeacherName());
        return ResponseResult.success(courseInfoList);
    }

    @Override
    public ResponseResult getCourseInfoByKcbhAndKxh(GetCourseInfoByKcbhAndKxhBizRequest getCourseInfoByKcbhAndKxhBizRequest) {
        List<GetCourseInfoByKcbhAndKxhDTO> courseInfoList = courseMapper.getCourseInfoByKcbhAndKxh(getCourseInfoByKcbhAndKxhBizRequest);
        return ResponseResult.success(courseInfoList);
    }

    @Override
    public ResponseResult getAllCourse(GetAllCourseBizRequest getAllCourseBizRequest) {
        List<GetAllCourseDTO> getAllCourseDTOList = courseMapper.getAllCourse(getAllCourseBizRequest);
        return ResponseResult.success(getAllCourseDTOList);
    }

    @Override
    public ResponseResult getCourseTable(GetMyCourseTableBizRequest getMyCourseTableBizRequest) {
        String jwcUsername = getMyCourseTableBizRequest.getJwcUsername();
        String nowTerm = getMyCourseTableBizRequest.getNowTerm();
        // 取出来的元素是按照week和day升序排序的
        List<GetCourseTableDTO> getCourseTableDTOList = courseMapper.getCourseTable(jwcUsername, nowTerm);
        List<List<GetCourseTableDTO>> courseTableList = new ArrayList<>();
        int index = 0;
        for (int weekIndex = 0; weekIndex < 25; weekIndex++) {
            ArrayList<GetCourseTableDTO> courseTableInAWeek = new ArrayList<>();
            int week = weekIndex + 1;
            while (index < getCourseTableDTOList.size()) {
                GetCourseTableDTO courseTable = getCourseTableDTOList.get(index);
                if (week == courseTable.getWeek()) {
                    courseTableInAWeek.add(getCourseTableDTOList.get(index));
                    index++;
                } else {
                    break;
                }
            }
            courseTableList.add(courseTableInAWeek);
        }
        return ResponseResult.success(courseTableList);
    }

    @Override
    public ResponseResult getScoreInfo(String jwcUsername) {
        LambdaQueryWrapper<Score> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Score::getJwcUsername, jwcUsername);
        List<Score> scoreList = scoreMapper.selectList(lambdaQueryWrapper);

        Map<String, List<Score>> scoreGroupByTerm = scoreList.stream().collect(Collectors.groupingBy(Score::getKkxq));

        List<ScoreTableOneTermDTO> scoreTableOneTermDTOList = scoreGroupByTerm.entrySet().stream().map(entry -> {
            ScoreTableOneTermDTO scoreTableOneTermDTO = new ScoreTableOneTermDTO();
            String term = entry.getKey();
            scoreTableOneTermDTO.setTerm(term);

            List<Score> scores = entry.getValue();
            List<ScoreTableDetailDTO> scoreTableDetailDTOList = new ArrayList<>();
            scores.forEach(score -> {
                ScoreTableDetailDTO scoreTableDetailDTO = toScoreTableDetailDTO(score);
                scoreTableDetailDTOList.add(scoreTableDetailDTO);
            });
            // 成绩从高到低排序
            scoreTableDetailDTOList.sort((o1, o2) -> o2.getCj().compareTo(o1.getCj()));
            scoreTableOneTermDTO.setScoreTableDetailDTOList(scoreTableDetailDTOList);
            return scoreTableOneTermDTO;
        }).collect(Collectors.toList());

        // 开课学期从高到低排序
        scoreTableOneTermDTOList.sort((o1, o2) -> o2.getTerm().compareTo(o1.getTerm()));
        ScoreTableAllTermDTO scoreTableAllTermDTO = new ScoreTableAllTermDTO();
        scoreTableAllTermDTO.setScoreTableOneTermDTOList(scoreTableOneTermDTOList);

        // 按照学期分组
        return ResponseResult.success(scoreTableAllTermDTO);
    }

    @Override
    public ResponseResult getRecentCourse(GetRecentCourseBizRequest getRecentCourseBizRequest) {
        List<GetRecentCourseDTO> courseList = courseMapper.getRecentCourse(getRecentCourseBizRequest);
        return ResponseResult.success(courseList);
    }

    @Override
    public ResponseResult savePersonJwcInfo(FirstLoginJwcRespTO firstLoginJwcRespTO) throws SysException {
        FirstLoginJwcRespDataTO data = firstLoginJwcRespTO.getData();
        JwcPersonInfoTO jwcPersonInfoTO = data.getJwcPersonInfo();
        List<CourseDetailTO> courseDetailTOList = data.getCourseDetailList();
        List<ScoreTO> scoreTOList = data.getScoreList();
        List<CourseAndTimeWithPlaceTO> courseAndTeacherWithPlaceTOList = data.getCourseAndTimeWithPlaceList();
        List<CourseAndTeacherTO> courseAndTeacherTOList = data.getCourseAndTeacherList();
        List<ExamScheduleTO> examScheduleTOList = data.getExamScheduleList();

        JwcPersonInfo jwcPersonInfo = toJwcPersonInfo(jwcPersonInfoTO);
        List<Course> courseList = toCourseList(courseDetailTOList);
        List<Score> scoreList = toScoreList(scoreTOList);
        List<CourseAndTimeWithPlace> courseAndTimeWithPlaceList = toCourseAndTimeWithPlaceList(courseAndTeacherWithPlaceTOList);
        List<CourseAndTeacher> courseAndTeacherList = toCourseAndTeacherList(courseAndTeacherTOList);
        List<ExamSchedule> examScheduleList = toExamScheduleList(examScheduleTOList);

        fillScoreTeacherName(scoreList, courseAndTeacherList);

        long t1, t2;
        // 保存数据
        t1 = System.currentTimeMillis();
        try {
            saveJwcPersonInfo(jwcPersonInfo);
        } catch (Exception e) {
            throw new SysException("保存用户教务处信息异常", e);
        }
        t2 = System.currentTimeMillis();
//        System.out.println("saveJwcPersonInfo执行时间:" + (t2 - t1) + "毫秒");

        t1 = System.currentTimeMillis();
        try {
            saveScoreList(scoreList);
        } catch (Exception e) {
            throw new SysException("保存成绩异常", e);
        }
        t2 = System.currentTimeMillis();
//        System.out.println("saveScoreList执行时间:" + (t2 - t1) + "毫秒");

        t1 = System.currentTimeMillis();
        try {
            saveCourseList(courseList);
            t2 = System.currentTimeMillis();
//            System.out.println("saveCourseList执行时间:" + (t2 - t1) + "毫秒");
            t1 = System.currentTimeMillis();
            saveCourseAndTimeWithPlaceList(courseAndTimeWithPlaceList);
            t2 = System.currentTimeMillis();
//            System.out.println("saveCourseAndTimeWithPlaceList执行时间:" + (t2 - t1) + "毫秒");
            t1 = System.currentTimeMillis();
            saveCourseAndTeacherList(courseAndTeacherList);
            t2 = System.currentTimeMillis();
//            System.out.println("saveCourseAndTeacherList执行时间:" + (t2 - t1) + "毫秒");
        } catch (Exception e) {
            throw new SysException("保存课程信息异常");
        }

        try {
            saveExamScheduleList(examScheduleList);
        } catch (Exception e) {
            throw new SysException("保存考试安排异常");
        }

        return ResponseResult.success();
    }

    @Override
    public ResponseResult getCourseInfoByKcmcAndTeacherName(GetCourseInfoByKcmcAndTeacherNameBizRequest getCourseInfoByKcmcAndTeacherNameBizRequest) {
        List<GetCourseInfoByKcmcAndTeacherNameDTO> getCourseInfoByKcmcAndTeacherNameDTOList = courseMapper.getCourseInfoByKcmcAndTeacherName(getCourseInfoByKcmcAndTeacherNameBizRequest);
        return ResponseResult.success(getCourseInfoByKcmcAndTeacherNameDTOList);
    }

    @Override
    public ResponseResult getCourseHistoryInfoByKcmcAndTeacherName(GetCourseHistoryInfoByKcmcAndTeacherNameBizRequest getCourseHistoryInfoByKcmcAndTeacherNameBizRequest) {
        List<GetCourseHistoryInfoByKcmcAndTeacherNameDTO> getCourseHistoryInfoByKcmcAndTeacherNameDTOList = courseMapper.getCourseHistoryInfoByKcmcAndTeacherName(getCourseHistoryInfoByKcmcAndTeacherNameBizRequest);
        return ResponseResult.success(getCourseHistoryInfoByKcmcAndTeacherNameDTOList);
    }

    @Override
    public ResponseResult getRecentExam() throws BizException {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        Long userId = userContextDTO.getUserId();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getJwcUsername).eq(User::getUId, userId);
        User user = userService.getOne(userLambdaQueryWrapper);
        if (user == null) {
            throw new BizException(CommonConstant.USER_ID_NOT_EXIST);
        }
        String jwcUsername = user.getJwcUsername();
        LambdaQueryWrapper<ExamSchedule> examScheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        examScheduleLambdaQueryWrapper.orderByDesc(ExamSchedule::getKssj).eq(ExamSchedule::getJwcUsername, jwcUsername);
        List<ExamSchedule> examScheduleList = examScheduleMapper.selectList(examScheduleLambdaQueryWrapper);
        return ResponseResult.success(examScheduleList);
    }

    /**
     * score表中填充teacher_name
     * 通过score表的kcbh，去courseAndTeacher中找teacher_name
     * @param scoreList
     * @param courseAndTeacherList
     */
    private void fillScoreTeacherName(List<Score> scoreList, List<CourseAndTeacher> courseAndTeacherList) {
        scoreList.forEach(score -> {
            courseAndTeacherList.forEach(courseAndTeacher -> {
                if (Objects.equals(score.getKcbh(), courseAndTeacher.getKcbh())) {
                    score.setTeacherName(courseAndTeacher.getTeacherName());
                }
            });
        });
    }

    private void saveJwcPersonInfo(JwcPersonInfo jwcPersonInfo) {
        LambdaUpdateWrapper<JwcPersonInfo> jwcPersonInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        jwcPersonInfoLambdaUpdateWrapper
                .set(JwcPersonInfo::getPassword, jwcPersonInfo.getPassword())
                .set(JwcPersonInfo::getRealName, jwcPersonInfo.getRealName())
                .set(JwcPersonInfo::getSchool, jwcPersonInfo.getSchool())
                .set(JwcPersonInfo::getMajor, jwcPersonInfo.getMajor())
                .eq(JwcPersonInfo::getJwcUsername, jwcPersonInfo.getJwcUsername());
        int affectedRowCount = jwcPersonInfoMapper.update(jwcPersonInfoLambdaUpdateWrapper);
        if (affectedRowCount == 0) {  // 更新失败，说明数据还不存在
            jwcPersonInfoMapper.insert(jwcPersonInfo);
        }
    }

    private void saveScoreList(List<Score> scoreList) throws InterruptedException {
        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(scoreList.size());
        scoreList.forEach(score -> {
            ThreadUtil.execute(() -> {
                try {
                    // 先update，如果失败，再insert
                    LambdaUpdateWrapper<Score> scoreLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                    scoreLambdaUpdateWrapper  // 主要应对后期改动成绩的情况
                            .set(Score::getCj, score.getCj())
                            .set(Score::getCjbs, score.getCjbs())
                            .set(Score::getCjzw, score.getCjzw())
                            .eq(Score::getJwcUsername, score.getJwcUsername())
                            .eq(Score::getKcmc, score.getKcmc());
                    int affectedRowCount = scoreMapper.update(scoreLambdaUpdateWrapper);
                    if (affectedRowCount == 0) {  // 更新失败，插入数据
                        scoreMapper.insert(score);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            });
        });
        countDownLatch.await();
    }

    private void saveCourseList(List<Course> courseList) throws InterruptedException {
        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(courseList.size());
        courseList.forEach(course -> {
            ThreadUtil.execute(() -> {
                try {
                    LambdaQueryWrapper<Course> courseLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    // 这里kcbh和kxh并不能确定一门课，思考需要修改吗
                    courseLambdaQueryWrapper
                            .select(Course::getKcbh)
                            .eq(Course::getKcbh, course.getKcbh())
                            .eq(Course::getKxh, course.getKxh());
                    Long courseCount = courseMapper.selectCount(courseLambdaQueryWrapper);
                    if (courseCount == 0) {
                        courseMapper.insert(course);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            });
        });
        countDownLatch.await();
    }

    private void saveExamScheduleList(List<ExamSchedule> examScheduleList) {
        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(examScheduleList.size());
        examScheduleList.forEach(examSchedule -> {
            ThreadUtil.execute(() -> {
                try {
                    LambdaQueryWrapper<ExamSchedule> examScheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    examScheduleLambdaQueryWrapper.select(ExamSchedule::getKcmc)
                            .eq(ExamSchedule::getKscc, examSchedule.getKscc())
                            .eq(ExamSchedule::getJwcUsername, examSchedule.getJwcUsername())
                            .eq(ExamSchedule::getKcmc, examSchedule.getKcmc());
                    Long count = examScheduleMapper.selectCount(examScheduleLambdaQueryWrapper);
                    if (count == 0) {
                        examScheduleMapper.insert(examSchedule);
                    }
                } finally {
                    countDownLatch.countDown();;
                }
            });
        });
    }

    private void saveCourseAndTimeWithPlaceList(List<CourseAndTimeWithPlace> courseAndTimeWithPlaceList) throws InterruptedException {
        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(courseAndTimeWithPlaceList.size());
        courseAndTimeWithPlaceList.forEach(courseAndTimeWithPlace -> {
            ThreadUtil.execute(() -> {
                try {
                    LambdaQueryWrapper<CourseAndTimeWithPlace> courseAndTimeWithPlaceLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    courseAndTimeWithPlaceLambdaQueryWrapper
                            .select(CourseAndTimeWithPlace::getKcbh)
                            .eq(CourseAndTimeWithPlace::getKcbh, courseAndTimeWithPlace.getKcbh())
                            .eq(CourseAndTimeWithPlace::getKxh, courseAndTimeWithPlace.getKxh())
                            .eq(CourseAndTimeWithPlace::getWeek, courseAndTimeWithPlace.getWeek())
                            .eq(CourseAndTimeWithPlace::getDay, courseAndTimeWithPlace.getDay())
                            .eq(CourseAndTimeWithPlace::getStartSection, courseAndTimeWithPlace.getStartSection())
                            .eq(CourseAndTimeWithPlace::getEndSection, courseAndTimeWithPlace.getEndSection());
                    Long courseAndTimeWithPlaceCount = courseAndTimeWithPlaceMapper.selectCount(courseAndTimeWithPlaceLambdaQueryWrapper);
                    if (courseAndTimeWithPlaceCount == 0) {
                        courseAndTimeWithPlaceMapper.insert(courseAndTimeWithPlace);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            });
        });
        countDownLatch.await();
    }

    private void saveCourseAndTeacherList(List<CourseAndTeacher> courseAndTeacherList) throws InterruptedException {
        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(courseAndTeacherList.size());
        courseAndTeacherList.forEach(courseAndTeacher -> {
            ThreadUtil.execute(() -> {
                try {
                    LambdaQueryWrapper<CourseAndTeacher> courseAndTeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    courseAndTeacherLambdaQueryWrapper.select(CourseAndTeacher::getTeacherName)
                            .eq(CourseAndTeacher::getKcbh, courseAndTeacher.getKcbh())
                            .eq(CourseAndTeacher::getKxh, courseAndTeacher.getKxh())
                            .eq(CourseAndTeacher::getTeacherName, courseAndTeacher.getTeacherName());
                    Long courseAndTeacherCount = courseAndTeacherMapper.selectCount(courseAndTeacherLambdaQueryWrapper);
                    if (courseAndTeacherCount == 0) {
                        courseAndTeacherMapper.insert(courseAndTeacher);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            });
        });
        countDownLatch.await();
    }

    private JwcPersonInfo toJwcPersonInfo(JwcPersonInfoTO jwcPersonInfoTO) {
        JwcPersonInfo jwcPersonInfo = new JwcPersonInfo();
        if (jwcPersonInfoTO == null) {
            return jwcPersonInfo;
        }
        jwcPersonInfo.setJwcUsername(jwcPersonInfoTO.getJwcUsername());
        jwcPersonInfo.setPassword(jwcPersonInfoTO.getPassword());
        jwcPersonInfo.setRealName(jwcPersonInfoTO.getRealName());
        jwcPersonInfo.setGender(jwcPersonInfoTO.getGender());
        jwcPersonInfo.setSchool(jwcPersonInfoTO.getSchool());
        jwcPersonInfo.setMajor(jwcPersonInfoTO.getMajor());
        return jwcPersonInfo;
    }

    private Course toCourse(CourseDetailTO courseDetailTO) {
        Course course = new Course();
        if (courseDetailTO == null) {
            return course;
        }
        course.setKcbh(courseDetailTO.getKcbh());
        course.setKxh(courseDetailTO.getKxh());
        course.setKcmc(courseDetailTO.getKcmc());
        course.setXf(BigDecimal.valueOf(courseDetailTO.getXf()));
        course.setKcsx(courseDetailTO.getKcsx());
        course.setXkjd(courseDetailTO.getXkjd());
        return course;
    }

    private List<Course> toCourseList(List<CourseDetailTO> courseDetailTOList) {
        return courseDetailTOList.stream().map(this::toCourse).collect(Collectors.toList());
    }

    private Score toScore(ScoreTO scoreTO) {
        Score score = new Score();
        if (scoreTO == null) {
            return score;
        }
        score.setXh(scoreTO.getXh());
        score.setKkxq(scoreTO.getKkxq());
        score.setKcbh(scoreTO.getKcbh());
        score.setKcmc(scoreTO.getKcmc());
        score.setCj(new BigDecimal(scoreTO.getCj()));
        score.setCjbs(scoreTO.getCjbs());
        score.setXf(BigDecimal.valueOf((scoreTO.getXf())));
        score.setZxs(scoreTO.getZxs());
        score.setKhfs(scoreTO.getKhfs());
        score.setKcsx(scoreTO.getKcsx());
        score.setKcxz(scoreTO.getKcxz());
        score.setJwcUsername(scoreTO.getJwcUsername());
        score.setCjzw(scoreTO.getCjzw());
        return score;
    }

    private List<Score> toScoreList(List<ScoreTO> scoreTOList) {
        return scoreTOList.stream().map(this::toScore).collect(Collectors.toList());
    }

    private CourseAndTimeWithPlace toCourseAndTimeWithPlaceList(CourseAndTimeWithPlaceTO courseAndTimeWithPlaceTO) {
        CourseAndTimeWithPlace courseAndTimeWithPlace = new CourseAndTimeWithPlace();
        if (courseAndTimeWithPlaceTO == null) {
            return courseAndTimeWithPlace;
        }
        courseAndTimeWithPlace.setKcmc(courseAndTimeWithPlaceTO.getKcmc());
        courseAndTimeWithPlace.setKcbh(courseAndTimeWithPlaceTO.getKcbh());
        courseAndTimeWithPlace.setKxh(courseAndTimeWithPlaceTO.getKxh());
        courseAndTimeWithPlace.setWeek(courseAndTimeWithPlaceTO.getWeek());
        courseAndTimeWithPlace.setDay(courseAndTimeWithPlaceTO.getDay());
        courseAndTimeWithPlace.setStartSection(courseAndTimeWithPlaceTO.getStartSection());
        courseAndTimeWithPlace.setEndSection(courseAndTimeWithPlaceTO.getEndSection());
        courseAndTimeWithPlace.setPlace(courseAndTimeWithPlaceTO.getPlace());
        return courseAndTimeWithPlace;
    }

    private List<CourseAndTimeWithPlace> toCourseAndTimeWithPlaceList(List<CourseAndTimeWithPlaceTO> courseAndTimeWithPlaceTOList) {
        return courseAndTimeWithPlaceTOList.stream().map(this::toCourseAndTimeWithPlaceList).collect(Collectors.toList());
    }

    private CourseAndTeacher toCourseAndTeacher(CourseAndTeacherTO courseAndTeacherTO) {
        CourseAndTeacher courseAndTeacher = new CourseAndTeacher();
        if (courseAndTeacherTO == null) {
            return courseAndTeacher;
        }
        courseAndTeacher.setKcbh(courseAndTeacherTO.getKcbh());
        courseAndTeacher.setKxh(courseAndTeacherTO.getKxh());
        courseAndTeacher.setTeacherName(courseAndTeacherTO.getTeacherName());
        return courseAndTeacher;
    }

    private List<CourseAndTeacher> toCourseAndTeacherList(List<CourseAndTeacherTO> courseAndTeacherTOList) {
        return courseAndTeacherTOList.stream().map(this::toCourseAndTeacher).collect(Collectors.toList());
    }

    private ExamSchedule toExamSchedule(ExamScheduleTO examScheduleTO) {
        ExamSchedule examSchedule = new ExamSchedule();
        if (examScheduleTO == null) {
            return examSchedule;
        }
        examSchedule.setId(examScheduleTO.getId());
        examSchedule.setJwcUsername(examScheduleTO.getJwcUsername());
        examSchedule.setKscc(examScheduleTO.getKscc());
        examSchedule.setKcbh(examScheduleTO.getKcbh());
        examSchedule.setKcmc(examScheduleTO.getKcmc());
        examSchedule.setKssj(examScheduleTO.getKssj());
        examSchedule.setKc(examScheduleTO.getKc());
        examSchedule.setZwh(examScheduleTO.getZwh());
        examSchedule.setTerm(examScheduleTO.getTerm());
        return examSchedule;
    }
    private List<ExamSchedule> toExamScheduleList(List<ExamScheduleTO> examScheduleTOList) {
        return examScheduleTOList.stream().map(this::toExamSchedule).collect(Collectors.toList());
    }

    private ScoreTableDetailDTO toScoreTableDetailDTO(Score score) {
        ScoreTableDetailDTO scoreTableDetailDTO = new ScoreTableDetailDTO();
        scoreTableDetailDTO.setKcbh(score.getKcbh());
        scoreTableDetailDTO.setKcmc(score.getKcmc());
        scoreTableDetailDTO.setCj(score.getCj());
        scoreTableDetailDTO.setCjbs(score.getCjbs());
        scoreTableDetailDTO.setXf(score.getXf());
        scoreTableDetailDTO.setZxs(score.getZxs());
        scoreTableDetailDTO.setKhfs(score.getKhfs());
        scoreTableDetailDTO.setKcsx(score.getKcsx());
        scoreTableDetailDTO.setKcxz(score.getKcxz());
        scoreTableDetailDTO.setCjzw(score.getCjzw());
        // 由成绩得出gpa
        BigDecimal gpa = scoreToGpa(score.getCj());
        scoreTableDetailDTO.setGpa(gpa);
        return scoreTableDetailDTO;
    }

    private BigDecimal scoreToGpa(BigDecimal score) {
        if (score.intValue() >= 90) {
            return new BigDecimal("4");
        } else if (score.intValue() >= 85) {
            return new BigDecimal("3.7");
        } else if (score.intValue() >= 82) {
            return new BigDecimal("3.3");
        } else if (score.intValue() >= 78) {
            return new BigDecimal("3");
        } else if (score.intValue() >= 75) {
            return new BigDecimal("2.7");
        } else if (score.intValue() >= 72) {
            return new BigDecimal("2.3");
        } else if (score.intValue() >= 68) {
            return new BigDecimal("2");
        } else if (score.intValue() >= 64) {
            return new BigDecimal("1.5");
        } else if (score.intValue() >= 60) {
            return new BigDecimal("1");
        } else if (score.intValue() >= 0) {
            return new BigDecimal("0");
        } else {
            // todo
            return new BigDecimal("0");
        }
    }
}
