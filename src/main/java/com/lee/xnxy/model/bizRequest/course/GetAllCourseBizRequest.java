package com.lee.xnxy.model.bizRequest.course;

import lombok.Data;

@Data
public class GetAllCourseBizRequest {
    private Integer pageNumber;

    private Integer pageSize;

    private String keyword;
}
