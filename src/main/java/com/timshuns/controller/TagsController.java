package com.timshuns.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.timshuns.pojo.Tag;
import com.timshuns.service.TagService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TagsController {
  
  @Autowired
  private TagService tagService;

  @GetMapping({"/tags.html"})
  public String index(Model model) {
    log.info("tags");
    //取得全部
    List<Tag> tags = tagService.getAllTags();
    model.addAttribute("tags", tags);
    return "tags";
  }
}
