package com.learning.edubrains.config;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.learning.edubrains.model.User;
import com.learning.edubrains.service.IUserService;
import com.learning.edubrains.utils.EduAppServiceException;

@Aspect
@Configuration
public class UserAspect {

	private Logger log = LogManager.getLogger(UserAspect.class);

	@Autowired
	private IUserService usrService;

	@Before("execution(* com.learning.edubrains.service.impl.*.* (..))")
	public void befre(JoinPoint jp) {
		// Advice
		log.info("--- Before:: Inside {} method ---", jp.getSignature().getName());
	}

	@After("execution(* com.learning.edubrains.service.impl.*.* (..))")
	public void after(JoinPoint jp) {
		// Advice
		log.info("--- After:: Exiting {} method ---", jp.getSignature().getName());
	}

	@AfterReturning("execution(* com.learning.edubrains.controller.*.* (..))")
	public void afterReturning(JoinPoint jp) {
		// Advice
		log.info("--- afterReturning:: COMPLETED SUCCESSFULLY ---");
	}

	@AfterThrowing("execution(* com.learning.edubrains.controller.*.* (..))")
	public void afterThrowing(JoinPoint jp) {
		log.error("--- afterThrowing:: EXECUTION FAILED ---");
	}

	@Around("execution(* com.learning.edubrains.controller.*.* (..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		log.info("--- INSIDE AROUND METHOD ---");
		long startTime = System.currentTimeMillis();
		log.info("Start time:: {}", startTime);

		// Authentication
		User u = usrService.getLoggedInUser();
		log.info("logged in User:: {}", u);

		if (!Optional.ofNullable(u).isPresent())
			throw new EduAppServiceException("User needs to be logged In");

		Object val = null;
		val = pjp.proceed();
		log.info("Time taken:: {}", System.currentTimeMillis() - startTime);
		log.info("--- EXITING AROUND METHOD ---");
		return val;
	}
}
