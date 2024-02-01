package com.lee.xnxy.model.dto.home;

import lombok.Data;

@Data
public class GetUserInfoDTO {
    private String userId;
    private String username;
    private String jwcUsername;
    private Integer Money;
    private String photoImg;
    private String gender;
    private String power;
}
