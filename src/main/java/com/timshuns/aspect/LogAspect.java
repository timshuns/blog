package com.timshuns.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

  @Pointcut("execution(* com.timshuns..*.*(..))")
  public void log() {
    log.info("------log------");
  }

  @Before("log()")
  public void doBefore() {
    log.info("------doBefore------");
  }

  @After("log()")
  public void doAfter() {
    log.info("------doAfter------");
  }

  @AfterReturning(returning = "result", pointcut = "log()")
  public void doAferReturn(Object result) {
    log.info("result:{}", result);
  }
}
