package com.kailaisi.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    @Pointcut("execution(* com.kailaisi.service..*(..))")
    private void logger() {
    }


    @Before("logger()")
    public void beforeAdvice() {
        System.out.println("beforeAdvice");
    }

    @After("logger()")
    public void afterAdvice() {
        System.out.println("afterAdvice");
    }

}
