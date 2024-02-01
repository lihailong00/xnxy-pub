package com.lee.xnxy.enums;

import java.util.Objects;

/**
 * 低3位用于表示同一模块下的不同权限
 */
public enum AuthEnums {
    // 教务处认证模块(1xxx)
    JWC_ACCREDITED(1001, "通过教务处认证"),

    // 系统层级权限
    ADMIN(2001, "管理员"),

    BLACKLIST(2002, "黑名单用户")
    ;


    // 管理员模块
    private Integer code;

    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    AuthEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String fromKeys(Integer code) {
        AuthEnums[] authEnums = AuthEnums.values();
        for (AuthEnums authEnum  : authEnums) {
            if (Objects.equals(authEnum.code, code)) {
                return authEnum.getName();
            }
        }
        return "";
    }
}
