package com.lee.xnxy.util;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class DynamicDownloadUtil {
    @Data
    public static class TokenParam {
        private Long createTime;  // 毫秒
        private Integer downloadCount;
        private String filename;
    }

    public static ConcurrentHashMap<Integer, TokenParam> TOKEN_MAP = new ConcurrentHashMap<>();

    private static final Integer MAX_DOWNLOAD_COUNT = 5;

    private static final Long MAX_VALID_TIME_MILLS = TimeUnit.MINUTES.toMillis(5);

    private static final Integer MAX_GET_TOKEN_COUNT = 10;

    public static boolean checkToken(String token) {
        int tokenNumber = Integer.parseInt(token);
        TokenParam tokenParam = TOKEN_MAP.get(tokenNumber);
        if (! checkTokenParamValid(tokenParam)) {
            return false;
        }
        tokenParam.setDownloadCount(tokenParam.getDownloadCount() + 1);
        return true;
    }

    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    private static boolean checkTokenParamValid(TokenParam tokenParam) {
        if (tokenParam == null) {
            return false;
        }
        Long createTime = tokenParam.getCreateTime();
        Integer downloadCount = tokenParam.getDownloadCount();
        if (createTime == null || downloadCount == null) {
            return false;
        }
        if (! checkDownloadCountValid(downloadCount) || ! checkTimeValid(createTime, System.currentTimeMillis())) {
            return false;
        }
        return true;
    }

    private static boolean checkDownloadCountValid(int downloadCount) {
        return downloadCount < MAX_DOWNLOAD_COUNT;
    }

    private static Boolean checkTimeValid(long oldTimeMills, long nowTimeMills) {
        return nowTimeMills - oldTimeMills <= MAX_VALID_TIME_MILLS;
    }

    public static Integer getToken(String filename) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        TokenParam tokenParam = new TokenParam();
        tokenParam.setDownloadCount(0);
        tokenParam.setCreateTime(currentTimeMillis);
        tokenParam.setFilename(filename);
        Integer tokenNumber = null;
        for (int i = 0; i < MAX_GET_TOKEN_COUNT; i++) {  // 仅尝试10次，如果都失败，则无法获取token。一般情况下不会发生。
            tokenNumber = generateRandomNumber();
            if (TOKEN_MAP.get(tokenNumber) != null) {
                TokenParam oldTokenParam = TOKEN_MAP.get(tokenNumber);
                if (! checkTokenParamValid(oldTokenParam)) {
                    TOKEN_MAP.remove(tokenNumber);
                }
            }
            if (TOKEN_MAP.get(tokenNumber) != null) {
                continue;
            }
            TOKEN_MAP.put(tokenNumber, tokenParam);
        }
        if (TOKEN_MAP.get(tokenNumber) == null) {
            throw new Exception("系统内部token积压过多，导致获取token失败");
        }
        assert tokenNumber != null;
        TOKEN_MAP.put(tokenNumber, tokenParam);
        return tokenNumber;
    }
}