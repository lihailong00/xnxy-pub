package com.lee.xnxy.util;

import com.lee.xnxy.constant.CommonConstant;

import java.util.UUID;

public class GeneratorUtil {
    public static String generateDefaultUsername() {
        return CommonConstant.DEFAULT_USER_PREFIX + UUID.randomUUID().toString().substring(0, 8);
    }
}
