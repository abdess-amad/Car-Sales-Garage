package com.devsling.car_sales_garage.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("execution(* com.devsling.car_sales_garage.web.*CarRestApi.*(..))")
	public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		String methodName = joinPoint.getSignature().toShortString();
		String inputArguments = Arrays.toString(joinPoint.getArgs());
		String output = (result != null) ? result.toString() : "null";

		logger.info("***************************************DEBUT *****************************************");
		logger.info("METHOD          : {}", methodName);
		logger.info("INPUT ARGUMENTS : {}", inputArguments);
		logger.info("OUTPUT          : {}", output);
		logger.info("EXECUTION TIME  : {}ms", executionTime);
		logger.info("***************************************FIN********************************************");

		return result;
	}

}
