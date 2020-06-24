package com.timshuns.admin.controller;

import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Type;
import com.timshuns.service.TypeService;
import com.timshuns.util.PageUtil;

/** 類別管理 */
@Controller
@RequestMapping("/admin/types")
public class TypeController {

  @Autowired private TypeService typeService;

  /** 類別起始頁面 */
  @GetMapping("/index")
  public String readTypePage(
      Model model,
      @RequestParam(value = "pageNumber", required = false, defaultValue = "1") String pageNumber) {
    // 參數判斷
    Long pageNumberLong = 1L;

    try {
      pageNumberLong = Long.valueOf(pageNumber);
    } catch (NumberFormatException e) {
      // 轉換失敗，改用預設值
    }

    Page<Type> pages = typeService.getTypes(1L);
    pages.setCurrent(pageNumberLong);
    int[] pageNumbers = PageUtil.pageNumbers(pages);
    model.addAttribute("pages", pages);
    model.addAttribute("pageNumbers", pageNumbers);
    return "admin/types";
  }

  /** 新增類別 */
  @PostMapping("/save")
  @ResponseBody
  public ResponseEntity<String> saveType(
      @RequestParam("name") String name, @RequestParam("status") int status) {
    Type type = new Type();
    type.setName(name);
    type.setStatus(status);
    typeService.saveType(type);

    return ResponseEntity.ok("");
  }
}
