package com.lee.xnxy.model.bizRequest.course;

import lombok.Data;

@Data
public class GetMyCourseTableBizRequest {
    private String jwcUsername;

    private String nowTerm;
}
