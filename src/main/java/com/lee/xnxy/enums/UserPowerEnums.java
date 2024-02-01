package com.lee.xnxy.enums;


public enum UserPowerEnums {
    NON_AUTH_USER(0, "未认证"),
    AUTH_USER(1, "已认证"),
    ADMIN(2, "管理员"),
    BLACK_LIST_USER(-1, "黑名单");

    UserPowerEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private final Integer code;

    private final String name;

}
