package com.yang.middleware.hystrix;

import com.yang.middleware.hystrix.annotation.DoHystrix;
import com.yang.middleware.hystrix.value.impl.HystrixValueImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：杨超
 * @date: 2023/7/12
 * @Copyright：
 */
@Aspect
@Component
public class DoHystrixPoint {

    @Pointcut("@annotation(com.yang.middleware.hystrix.annotation.DoHystrix)")
    public void aopPoint(){
    }

    @Around("aopPoint() && @annotation(doGovern)")
    public Object doRouter(ProceedingJoinPoint joinPoint, DoHystrix doGovern) throws Throwable {
        HystrixValueImpl hystrixValue = new HystrixValueImpl();
        return hystrixValue.access(joinPoint,getMethod(joinPoint),doGovern,joinPoint.getArgs());
    }

    private Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }
}
