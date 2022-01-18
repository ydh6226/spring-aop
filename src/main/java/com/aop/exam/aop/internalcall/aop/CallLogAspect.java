package com.aop.exam.aop.internalcall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class CallLogAspect {

    @Before("execution(* com.aop.exam.aop.internalcall..*.*(..))")
    public void hello(JoinPoint joinPoint) {
        log.info("aop:{} ", joinPoint.getSignature());
    }
}
