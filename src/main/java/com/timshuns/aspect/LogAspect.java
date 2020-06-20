package com.timshuns.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Pointcut("execution(* com.timshuns..*.*(..))")
  public void log() {
    logger.info("------log------");
  }

  @Before("log()")
  public void doBefore() {
    logger.info("------doBefore------");
  }

  @After("log()")
  public void doAfter() {
    logger.info("------doAfter------");
  }

  @AfterReturning(returning = "result", pointcut = "log()")
  public void doAferReturn(Object result) {
    logger.info("result:{}", result);
  }
}
