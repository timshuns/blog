package com.timshuns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.timshuns.aspect.LogAspect;

@Controller
public class IndexController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Autowired LogAspect aspect;
  
  @GetMapping("/{id}/{name}")
  public String test(@PathVariable int id, @PathVariable String name) {
    // int i = 9/0;
    System.err.println(aspect==null ); 
    System.out.println("id:" + id);
    System.out.println("name:" + name);
    logger.info("test");
    return "index";
  }
  @GetMapping("/")
  public String index() {
    // int i = 9/0;
    logger.info("index");
    return "index";
  }
}
