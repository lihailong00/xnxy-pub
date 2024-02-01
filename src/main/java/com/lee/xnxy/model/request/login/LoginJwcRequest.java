package com.lee.xnxy.model.request.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("LoginJwcRequest: 用户第一次登陆教务处。")
@Data
public class LoginJwcRequest {
    @ApiModelProperty("教务处用户名")
    @NotEmpty
    private String username;

    @ApiModelProperty("教务处密码")
    @NotEmpty
    private String password;
}
