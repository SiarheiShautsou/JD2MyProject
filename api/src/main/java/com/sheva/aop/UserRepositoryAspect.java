package com.sheva.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class UserRepositoryAspect {

    private static final Logger log = Logger.getLogger(UserRepositoryAspect.class);

    @Pointcut("execution(* com.sheva.repository.UserSpringDataRepository.*(..))")
    public void aroundRepositoryPointCut() {
    }

    @Around("aroundRepositoryPointCut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Method " + joinPoint.getSignature().getName() + " start");

        Object proceed = joinPoint.proceed();

        log.info("Method" + joinPoint.getSignature().getName() + " finished");

        return proceed;
    }

}
