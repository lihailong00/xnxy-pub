package com.lee.xnxy.handler;

import cn.hutool.json.JSONUtil;
import com.lee.xnxy.exception.BizException;
import com.lee.xnxy.exception.SysException;
import com.lee.xnxy.model.dto.ResponseResult;
import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.util.UserContextDTOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BizException.class)
    public ResponseResult bizException(BizException e) {  // 返回值通常是对象，可以自定义ResponseResult
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        log.error("业务异常，提示信息: {}\n当前用户信息: {}\n异常堆栈: ", e.getMessage(), JSONUtil.toJsonStr(userContextDTO), e);
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(value = SysException.class)
    public ResponseResult sysException(SysException e) {
        UserContextDTO userContextDTO = UserContextDTOUtil.getUserContextDTO();
        log.error("系统异常，提示信息: {}\n当前用户信息: {}\n异常堆栈: ", e.getMessage(), JSONUtil.toJsonStr(userContextDTO), e);
        return ResponseResult.fail(e.getMessage());
    }
}
