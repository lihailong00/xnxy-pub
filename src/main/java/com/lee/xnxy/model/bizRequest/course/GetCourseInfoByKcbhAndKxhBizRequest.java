package com.lee.xnxy.model.bizRequest.course;

import lombok.Data;

@Deprecated
@Data
public class GetCourseInfoByKcbhAndKxhBizRequest {
    private String kcbh;

    private Integer kxh;
}
