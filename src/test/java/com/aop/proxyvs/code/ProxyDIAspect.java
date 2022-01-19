package com.aop.proxyvs.code;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Import;

@Slf4j
@Aspect
public class ProxyDIAspect {

    @Before("execution(* com.aop..*.*(..))")
    public void doTrace(JoinPoint joinPoint) {
        log.info("[ProxyDIAspect] {}", joinPoint.getSignature());
    }
}
