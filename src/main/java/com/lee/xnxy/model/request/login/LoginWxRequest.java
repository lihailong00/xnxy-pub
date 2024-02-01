package com.lee.xnxy.model.request.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("LoginWxRequest: 登陆微信请求。")
@Data
public class LoginWxRequest {
    @ApiModelProperty("登陆微信服务器时需要的code，用户打开微信小程序后可以获得。")
    @NotNull
    private String jsCode;
}
