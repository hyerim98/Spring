package com.example.springintro.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP
@Aspect
// 스프링 빈으로 등록
@Component
public class TimeTraceAop {
    // @Around : AOP 타켓 지정
    @Around("execution(* com.example.springintro..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());

        try {
            // 다음 메소드 진행
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            long elapsed = end - start;
            System.out.println("END : " + joinPoint.toString() + " " + elapsed + "ms");
        }

    }
}
