package com.yang.middleware.hystrix.value;

import com.yang.middleware.hystrix.annotation.DoHystrix;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：杨超
 * @date: 2023/7/12
 * @Copyright：
 */
public interface IValueService {

    /**
     * 执行超时熔断操作
     * @param joinPoint
     * @param method
     * @param doHystrix
     * @param args
     * @return
     * @throws Throwable
     */
    Object access(ProceedingJoinPoint joinPoint, Method method, DoHystrix doHystrix, Object[] args) throws Throwable;
}
