package com.lee.xnxy.model.remote;

import com.lee.xnxy.model.dao.course.ExamSchedule;
import lombok.Data;

import java.util.List;

@Data
public class FirstLoginJwcRespDataTO {
    private JwcPersonInfoTO jwcPersonInfo;
    private List<ScoreTO> scoreList;
    private List<CourseDetailTO> courseDetailList;
    private List<CourseAndTimeWithPlaceTO> courseAndTimeWithPlaceList;
    private List<CourseAndTeacherTO> courseAndTeacherList;
    private List<ExamScheduleTO> examScheduleList;
}
