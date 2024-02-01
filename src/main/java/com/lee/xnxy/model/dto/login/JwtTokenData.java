package com.lee.xnxy.model.dto.login;

import lombok.Data;

@Data
public class JwtTokenData {
    private String uId;
    private String username;
    private String photoImg;
    @Deprecated
    private String power;
    private String money;
}
