package com.lee.xnxy.model.dto.trade;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ListGoodsDTO {
    private String gId;

    private String username;

    private String name;

    private String content;

    private BigDecimal price;

    private List<String> imageList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private Integer viewCount;
}
