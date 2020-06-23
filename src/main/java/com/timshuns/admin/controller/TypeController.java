package com.timshuns.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.timshuns.pojo.Type;
import com.timshuns.service.TypeService;

@Controller
@RequestMapping("/admin/types")
public class TypeController {

  @Autowired private TypeService typeService;

  @GetMapping({"/","/index"})
  public String types(Model model) {
    List<Type> types = typeService.getTypesWithPage(1L);
    model.addAttribute("types", types);
    return "admin/types";
  }
}
