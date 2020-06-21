package com.timshuns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping({"/","/index.html","index"})
  public String index() {
    logger.info("index");
    return "index";
  }
}
