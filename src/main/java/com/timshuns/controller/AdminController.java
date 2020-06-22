package com.timshuns.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.timshuns.pojo.User;
import com.timshuns.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

  @Autowired
  private UserService userService;

  @GetMapping({"/loginPage"})
  public String loginPage() {
    log.info("index");
    return "admin/login";
  }
  
  @GetMapping({"/index"})
  public String indexPage() {
    log.info("index");
    return "admin/index";
  }

  @PostMapping("/login")
  public String login(@RequestParam String userName, @RequestParam String password,
      HttpSession session) {
    User user = userService.checkUser(userName, password);
    if (user == null) {
      // 錯誤
      return "redirect:/admin/login";
    } else {
      user.setPassword(null);
      session.setAttribute("user", user);
      return "admin/index";
    }
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("user");
    return "redirect:/admin/";
  }
}
