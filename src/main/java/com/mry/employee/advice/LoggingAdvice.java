package com.mry.employee.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut("execution(* com.mry.employee.*.*.*(..))")
    public void allControllerMethods() {
    }

    @Around("allControllerMethods()")
    public Object logMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Entering method: " + methodName + "()"+ " with arguments: " + mapper.writeValueAsString(proceedingJoinPoint.getArgs()) + " in class: " + proceedingJoinPoint.getTarget().getClass().getName());
        Object result = proceedingJoinPoint.proceed();
        logger.info("Exiting method: " + methodName + "()"+ " with result: " + result + " in class: " + proceedingJoinPoint.getTarget().getClass().getName());
        return result;
    }
}
