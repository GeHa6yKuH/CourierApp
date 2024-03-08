package com.bogdan.courierapp.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * com.bogdan.courierapp.controller.rest.*.*(..))")
    public void logController() {
    }

    @Pointcut("execution(public * com.bogdan.courierapp.service.impl.*.*(..))")
    public void serviceLog() {
    }

    @Before("logController()")
    public void doBeforeControllerMethods(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{} HASHCODE : {}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), jp.hashCode());
    }


    @After("serviceLog()")
    public void doAfterServiceMethods(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{} TARGET : {}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), jp.getTarget());
    }

    @AfterReturning(returning = "returnObject", pointcut = "serviceLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("\nReturn value: {}\n" +
                        "END OF REQUEST",
                returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "logController()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }
}
