package dev.innocentudo.librarymanagementsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* dev.innocentudo.librarymanagementsystem..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* dev.innocentudo.librarymanagementsystem..*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        log.info("Exit: {}() with result = {}", joinPoint.getSignature().getName(), result);
    }

    @Around("execution(* dev.innocentudo.librarymanagementsystem..*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Exit: {}() with result = {} and time taken = {} ms", joinPoint.getSignature().getName(), result, timeTaken);
        return result;
    }
}
