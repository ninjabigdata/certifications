package com.pluralsight.springmvchibernate.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PSBankLoggingAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.pluralsight.springmvchibernate.controller.*.*(..))")
	private void forControllers() {
	}

	@Pointcut("execution(* com.pluralsight.springmvchibernate.respositories.*.*(..))")
	private void forRepositories() {
	}

	@Pointcut("execution(* com.pluralsight.springmvchibernate.service.*.*(..))")
	private void forServices() {
	}

	@Pointcut("forControllers() || forRepositories() || forServices()")
	private void forApplication() {
	}

	@Before("forApplication()")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("Invoking the method {}", methodName);
	}

	@AfterReturning(pointcut = "forApplication()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("Data returned {} from the method {}", result, methodName);
	}

}
