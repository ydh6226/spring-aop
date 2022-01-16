package com.aop.pointcut;

import com.aop.member.MemberService;
import com.aop.member.annotation.ClassAop;
import com.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Parameter;

@Slf4j
@Import(ParameterTest.ParameterAspect.class)
@SpringBootTest
class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService proxy: {}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {

        @Pointcut("execution(* com.aop.member..*.*(..))")
        private void allMember() {}

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs]{}, arg: {}", joinPoint.getSignature(), arg1);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg, ..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, String arg) throws Throwable {
            log.info("[logArgs]{}, arg: {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg, ..)")
        public void logArgs3(String arg) throws Throwable {
            log.info("[logArgs] arg: {}", arg);
        }

        // 프록시 객체
        @Before("allMember() && this(obj)")
        public void logArgs4(JoinPoint joinPoint, MemberService obj) throws Throwable {
            log.info("[this]{}, obj: {}", joinPoint.getSignature(), obj.getClass());
        }

        // 실제 타겟 객체
        @Before("allMember() && target(obj)")
        public void logArgs5MemberService(JoinPoint joinPoint, MemberService obj) throws Throwable {
            log.info("[target]{}, obj: {}", joinPoint.getSignature(), obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinPoint, ClassAop annotation) throws Throwable {
            log.info("[atTarget]{}, obj: {}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void atWithin(JoinPoint joinPoint, ClassAop annotation) throws Throwable {
            log.info("[atWithin]{}, obj: {}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation) throws Throwable {
            log.info("[atAnnotation]{}, annotationValue: {}", joinPoint.getSignature(), annotation.value());
        }
    }

}
