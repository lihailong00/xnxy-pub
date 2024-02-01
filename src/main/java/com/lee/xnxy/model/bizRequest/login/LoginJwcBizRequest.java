package com.lee.xnxy.model.bizRequest.login;

import lombok.Data;

@Data
public class LoginJwcBizRequest {
    private Long userId;

    private String username;

    private String password;
}
