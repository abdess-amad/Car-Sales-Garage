package com.devsling.carSalesGarage.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	@Around("execution(* com.devsling.carSalesGarage.web.*CarRestApi.*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        String methodName = joinPoint.getSignature().toShortString();
        String inputArguments = Arrays.toString(joinPoint.getArgs());
        String output = (result != null) ? result.toString() : "null";
 
        // Log inputs, outputs, and processing time
        System.out.println("***************************************DEBUT *****************************************");
        System.out.println("METHOD          : " + methodName);
        System.out.println("INPUT ARGUMENTS : " + inputArguments);
        System.out.println("OUTPUT          : " + output);
        System.out.println("EXECUTION TIME  : " + executionTime + "ms");
        System.out.println("***************************************FIN********************************************");


        return result;
    }

}
