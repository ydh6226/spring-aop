package com.aop;

import com.aop.order.aop.AspectV1;
import com.aop.order.aop.AspectV3;
import com.aop.order.aop.AspectV4Pointcut;
import com.aop.order.aop.AspectV5Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AspectV5Order.LogAspect.class, AspectV5Order.TransactionAspect.class})
@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

}
