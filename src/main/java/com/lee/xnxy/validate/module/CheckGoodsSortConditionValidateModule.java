package com.lee.xnxy.validate.module;

import com.lee.xnxy.constant.CommonConstant;
import com.lee.xnxy.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CheckGoodsSortConditionValidateModule {
    public void validate(String condition) {
        if (Objects.equals(condition, CommonConstant.PRICE) ||
        Objects.equals(condition, CommonConstant.TIME)) {
            return;
        }
        throw new ValidateException(CommonConstant.ERROR_GOODS_PARAM);
    }
}
