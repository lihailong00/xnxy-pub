package com.lee.xnxy.aop;


import com.lee.xnxy.model.dto.UserContextDTO;
import com.lee.xnxy.util.UserContextDTOUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class TestAspect {
    @Pointcut("execution(* com.lee.xnxy.controller..*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object doPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        if (UserContextDTOUtil.getUserContextDTO() == null) {
            UserContextDTO userContextDTO = new UserContextDTO();
            userContextDTO.setUserId(-1L);
            userContextDTO.setUsername("测试账号");
            UserContextDTOUtil.setUserContextDTO(userContextDTO);
        }
        Object result = joinPoint.proceed();
        return result;
    }
}
