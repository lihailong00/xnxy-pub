package com.lee.xnxy.model.bizRequest.trade;

import lombok.Data;

@Data
public class ListGoodsBizRequest {
    /**
     * 排序方式
     * time
     * price
     */
    private String cond;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 页码
     */
    private Integer pageNumber;

    /**
     * 页面大小
     */
    private Integer pageSize;

    private Long userId;
}
