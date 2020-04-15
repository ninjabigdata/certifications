package org.mindtree.com.assessment.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ApplicationAspects {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Before(value = "execution(* org.mindtree.com.assessment..*.*(..))")
	public void logBeforeInvoking(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		logger.debug("Invoking {} method", methodName);
	}

	@After(value = "execution(* org.mindtree.com.assessment..*.*(..))")
	public void logAfterExiting(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("Exiting {} method", methodName);
	}

	@AfterThrowing(value = "execution(* org.mindtree.com.assessment..*.*(..))", throwing = "exception")
	public void logError(Exception exception) throws Throwable {
		logger.error("Exception thrown is ", exception);
	}

}
