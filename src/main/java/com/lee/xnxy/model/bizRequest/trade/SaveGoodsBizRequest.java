package com.lee.xnxy.model.bizRequest.trade;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaveGoodsBizRequest {
    /**
     * 发布者id
     */
    private Long uId;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品名
     */
    private String content;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品图片链接
     */
    private List<String> imageList;

    /**
     * 商品发布ip
     */
    private String ip;

    private Integer hasDeleted;

    private LocalDateTime createTime;

}
