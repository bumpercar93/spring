package kr.or.ddit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		logger.debug("Profiling Aspect around method before");
		
		Object[] args = joinPoint.getArgs();
		Object returnVal = joinPoint.proceed(args);
		
		long endTime = System.currentTimeMillis();
		logger.debug("endTime - startTime : {}", (endTime - startTime) + "ms");
		logger.debug("Profiling Aspect around method after");
		
		return returnVal;
	}

}
