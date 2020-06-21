package com.timshuns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping({"/about.html"})
  public String index() {
    logger.info("about");
    return "about";
  }
}
