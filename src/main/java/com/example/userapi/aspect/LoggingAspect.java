package com.example.userapi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.example.userapi.controller..*(..)) || " +
            "execution(* com.example.userapi.service..*(..)) || " +
            "execution(* com.example.userapi.repositories..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        log.info("[{}] START - Args: {}",
                methodName,
                Arrays.toString(joinPoint.getArgs())
        );

        try {
            Object result = joinPoint.proceed();
            log.info("[{}} END - Result: {}",
                    methodName,
                    result
            );
            return result;
        } catch (Exception e) {
            log.warn("[{}} EXCEPTION - Message: {}",
                    methodName,
                    e.getMessage()
            );
            throw e;
        }
    }
}
