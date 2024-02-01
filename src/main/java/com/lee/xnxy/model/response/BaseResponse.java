package com.lee.xnxy.model.response;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private Status status;

    private T data;

    @Data
    private static class Status {
        Integer code;

        String message;

        String failureInfo;
    }
}
