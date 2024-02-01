package com.lee.xnxy.enums;

import java.util.Objects;

public enum GenderEnums {
    MALE(1, "男"),
    FEMALE(0, "女"),
    UNKNOWN(-1, "");

    private final Integer code;

    private final String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    GenderEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String fromKeys(Integer code) {
        GenderEnums[] genderEnums = GenderEnums.values();
        for (GenderEnums genderEnum : genderEnums) {
            if (Objects.equals(genderEnum.code, code)) {
                return genderEnum.getName();
            }
        }
        return "";
    }
}
