package com.lee.xnxy.model.bizRequest.home;

import lombok.Data;

@Data
public class SaveUserInfoBizRequest {
    private Long userId;

    private String username;

    private Integer gender;

    private String photoImg;
}
