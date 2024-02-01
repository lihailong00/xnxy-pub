package com.lee.xnxy.model.request.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@ApiModel("SaveUserInfoRequest: 更新当前用户信息。")
@Data
public class SaveUserInfoRequest {
    @ApiModelProperty("用户名")
    @Length(min = 1, max = 30, message = "用户名长度1-30字")
    private String username;

    @ApiModelProperty("用户性别，枚举男/女")
    private String gender;

    @ApiModelProperty("用户头像，只能有一张图片地址")
    private String photoImg;
}
