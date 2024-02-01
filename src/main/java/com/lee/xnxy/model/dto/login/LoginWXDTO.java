package com.lee.xnxy.model.dto.login;

import lombok.Data;

@Data
public class LoginWXDTO {
    private String token;

    private String userId;

    private String username;

    private Boolean isLoginJwc;
}
