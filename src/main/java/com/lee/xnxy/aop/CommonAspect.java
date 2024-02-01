package com.lee.xnxy.aop;

import com.lee.xnxy.exception.ValidateException;
import com.lee.xnxy.model.dto.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(2)
public class CommonAspect {
    @Resource
    private ApplicationContext applicationContext;

    @Pointcut("execution(* com.lee.xnxy.controller..*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")  // lhl todo 了解用法
    public Object doPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        try {
            validate(clazz, method, args);
        } catch (ValidateException validateException) {
            return ResponseResult.fail(validateException.getMessage());
        }
        Object result = joinPoint.proceed();
        return result;
    }

    private void validate(Class<?> clazz, Method method, Object[] args) throws Throwable {
        // 方法上标注类@DynamicAnnotation才会执行动态校验
        DynamicValidate annotation = AnnotationUtils.findAnnotation(method, DynamicValidate.class);
        if (annotation == null) {
            return;
        }
        // 获取校验类的bean对象
        String validatorBeanName = StringUtils.uncapitalize(clazz.getSimpleName() + "Validator");
        Object validatorBean = applicationContext.getBean(validatorBeanName);
        // 获取校验方法
        String validatorMethodName = method.getName();
        Method validatorMethod = validatorBean.getClass().getMethod(validatorMethodName, method.getParameterTypes());

        // 方法中的Exception被InvocationTargetException包裹，因此需要取出原始的Exception并抛出
        try {
            validatorMethod.invoke(validatorBean, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}
