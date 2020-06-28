package com.timshuns.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Tag;
import com.timshuns.service.TagService;
import com.timshuns.util.PageUtil;

@Controller
@RequestMapping("/admin/tags")
public class TagController {
  @Autowired
  private TagService tagService;

  /** 類別起始頁面 */
  @GetMapping("/index")
  public String readTagPage(Model model,
      @RequestParam(value = "pageNumber", required = false) String pageNumber) {
    // 參數判斷
    Long pageNumberLong = 1L;

    try {
      pageNumberLong = Long.valueOf(pageNumber);
    } catch (NumberFormatException e) {
      // 轉換失敗，改用預設值
    }

    Page<Tag> pages = tagService.getTags(pageNumberLong);

    // 查無資料
    if (pages == null) {
      model.addAttribute("pages", null);
      return "admin/tags";
    }

    // 判斷當前頁數是否正確
    if (pageNumberLong > pages.getPages()) {
      pages = tagService.getTags(pages.getPages());
    } else if (pageNumberLong <= 0) {
      pages.setCurrent(1L);
    } else {
      pages.setCurrent(pageNumberLong);
    }
    List<Integer> pageNumbers = PageUtil.pageNumbers(pages);
    model.addAttribute("pages", pages);
    model.addAttribute("pageNumbers", pageNumbers);
    return "admin/tags";
  }

  /** 新增類別 */
  @PostMapping("/save")
  @ResponseBody
  public ResponseEntity<String> saveTag(@RequestParam("name") String name) {
    Tag tag = new Tag();
    tag.setName(name);

    if (tagService.saveTag(tag)) {
      return ResponseEntity.ok("新增成功");
    } else {
      return new ResponseEntity<String>("新增失敗", HttpStatus.BAD_REQUEST);
    }

  }

  /** 修改類別 */
  @PutMapping("/update")
  @ResponseBody
  public ResponseEntity<String> updateTag(
      @RequestParam(required = false, name = "name") String name, @RequestParam("id") long id) {
    Tag tag = new Tag();
    tag.setId(id);
    tag.setName(name);
    if (tagService.updateTag(tag)) {
      return ResponseEntity.ok("修改成功");
    } else {
      return new ResponseEntity<String>("修改失敗", HttpStatus.BAD_REQUEST);
    }
  }

  /** 刪除類別 */
  @DeleteMapping("/delete")
  @ResponseBody
  public ResponseEntity<String> deleteTag(@RequestParam("id") long id) {
    if (tagService.deleteTag(id)) {
      return ResponseEntity.ok("刪除成功");
    } else {
      return new ResponseEntity<String>("刪除失敗", HttpStatus.BAD_REQUEST);
    }
  }
}
