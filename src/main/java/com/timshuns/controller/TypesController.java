package com.timshuns.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.timshuns.pojo.Tag;
import com.timshuns.service.TagService;

@Controller
public class TypesController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping({"/types.html"})
  public String index(Model model) {
    logger.info("types");
    return "types";
  }
}
