package com.lee.xnxy.validate.module;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidPaperDownloadWayModule {
    public void validate(String downloadWay) {
        if (Objects.equals(downloadWay, CommonConstant.DOWNLOAD_BY_EMAIL) || Objects.equals(downloadWay, CommonConstant.DOWNLOAD_BY_WEB)) {
            return;
        }
        throw new ValidateException(CommonConstant.INVALID_DOWNLOAD_WAY);
    }
}
