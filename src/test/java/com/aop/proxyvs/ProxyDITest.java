package com.aop.proxyvs;

import com.aop.member.MemberService;
import com.aop.member.MemberServiceImpl;
import com.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
@Import(ProxyDIAspect.class)
class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class: {}", memberService.getClass());
        log.info("memberServiceImpl class: {}", memberServiceImpl.getClass());
        memberService.hello("hello");
        memberServiceImpl.hello("hello");
    }
}
