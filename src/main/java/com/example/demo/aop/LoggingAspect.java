package com.example.demo.aop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	@Before("execution(* com.example.demo.service.*.*(. .))")
	public void logBefore(JoinPoint joinPoint) {
		outputLog("メソッド開始", joinPoint);
	}

	@AfterReturning("execution(* com.example.demo.service.*.*(. .))")
	public void logAgter(JoinPoint joinPoint) {
		outputLog("メソッド終了", joinPoint);
	}
	
	private void outputLog(String str, JoinPoint joinPoint) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String strNow = LocalDateTime.now().format(formatter);
		
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println(
				strNow + " : " + str + " : " + className + " . " + methodName + "()" );
	}
}
