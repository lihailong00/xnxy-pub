package com.lee.xnxy.model.dto.paper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListPaperDTO {
    private String paId;

    private String paperName;

    private String paperDesc;

    private String author;

    private Integer price;

    private Integer viewCount;

    private Integer downloadCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private LocalDateTime updateTime;

    private String paperImage;
}
