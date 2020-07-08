package com.timshuns.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @Autowired
  private TypeService typeService;

  /** 類別起始頁面 */
  @GetMapping("/index")
  public String readTypePage(Model model,
      @RequestParam(value = "pageNumber", required = false) String pageNumber,
      @RequestParam(value = "typeName", required = false) String typeName,
      @RequestParam(value = "typeStatus", required = false, defaultValue = "-1") int typeStatus) {
    // 參數判斷
    Long pageNumberLong = 1L;

    try {
      pageNumberLong = Long.valueOf(pageNumber);
    } catch (NumberFormatException e) {
      // 轉換失敗，改用預設值
    }

    Page<Type> pages = typeService.getTypes(pageNumberLong, typeName, typeStatus);

    // 查無資料
    if (pages == null) {
      model.addAttribute("pages", null);
      return "admin/types";
    }

    // 判斷當前頁數是否正確
    if (pageNumberLong > pages.getPages()) {
      pages = typeService.getTypes(pages.getPages(), typeName, typeStatus);
    } else if (pageNumberLong <= 0) {
      pages.setCurrent(1L);
    } else {
      pages.setCurrent(pageNumberLong);
    }
    List<Integer> pageNumbers = PageUtil.pageNumbers(pages);
    model.addAttribute("pages", pages);
    model.addAttribute("typeName", typeName);
    model.addAttribute("typeStatus", typeStatus);
    model.addAttribute("pageNumbers", pageNumbers);
    return "admin/types";
  }

  /** 新增類別 */
  @PostMapping("/save")
  @ResponseBody
  public ResponseEntity<String> saveType(@RequestParam("name") String name,
      @RequestParam("status") int status) {
    Type type = new Type();
    type.setName(name);
    type.setStatus(status);

    if (typeService.saveType(type) > 0) {
      return ResponseEntity.ok("新增成功");
    } else {
      return new ResponseEntity<String>("新增失敗", HttpStatus.BAD_REQUEST);
    }
  }

  /** 修改類別 */
  @PutMapping("/update/{id}")
  @ResponseBody
  public ResponseEntity<String> updateType(
      @RequestParam(required = false, name = "name") String name,
      @RequestParam("status") int status, @PathVariable long id) {
    Type type = new Type();
    type.setId(id);
    type.setName(name);
    type.setStatus(status);
    if (typeService.updateType(type)) {
      return ResponseEntity.ok("修改成功");
    } else {
      return new ResponseEntity<String>("修改失敗", HttpStatus.BAD_REQUEST);
    }
  }

  /** 刪除類別 */
  @DeleteMapping("/delete/{id}")
  @ResponseBody
  public ResponseEntity<String> deleteType(@PathVariable long id) {
    if (typeService.deleteType(id)) {
      return ResponseEntity.ok("刪除成功");
    } else {
      return new ResponseEntity<String>("刪除失敗", HttpStatus.BAD_REQUEST);
    }
  }
}
