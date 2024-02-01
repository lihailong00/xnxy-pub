package com.lee.xnxy.model.dto.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListPostsDTO {
    private String pId;

    private String author;

    private String userImage;

    private String title;

    private String content;

    private Integer viewCount;

    private Integer likeCount;

    private Integer commentCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
