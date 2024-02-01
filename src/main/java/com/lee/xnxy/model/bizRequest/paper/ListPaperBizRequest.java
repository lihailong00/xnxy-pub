package com.lee.xnxy.model.bizRequest.paper;

import lombok.Data;

@Data
public class ListPaperBizRequest {
    private Integer pageNumber;

    private Integer pageSize;

    private String keyword;
}
