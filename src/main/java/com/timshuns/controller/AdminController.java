package com.timshuns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping({"/login.page"})
  public String index() {
    logger.info("index");
    return "admin/login";
  }
}
