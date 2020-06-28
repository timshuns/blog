package com.timshuns.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Blog;
import com.timshuns.pojo.Type;
import com.timshuns.service.BlogService;
import com.timshuns.service.TypeService;
import com.timshuns.util.PageUtil;

@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

  @Autowired
  private BlogService blogService;
  
  @Autowired
  private TypeService typeService;
  
  @GetMapping("/createPage")
  public String readBlogCreatePage(Model model) {
    //取得類別
    List<Type> types = typeService.getAllTypes();
    model.addAttribute("types", types);
    return "/admin/blogs-create";
  }
  
  /** 文章起始頁面 */
  @GetMapping("/index")
  public String readBlogPage(Model model,
      @RequestParam(value = "pageNumber", required = false) String pageNumber) {
    // 參數判斷
    Long pageNumberLong = 1L;

    try {
      pageNumberLong = Long.valueOf(pageNumber);
    } catch (NumberFormatException e) {
      // 轉換失敗，改用預設值
    }

    Page<Blog> pages = blogService.getBlogs(pageNumberLong);
    
    //查無資料
    if(pages == null) {
      model.addAttribute("pages", null);
      return "admin/blogs";
    }
    
    // 判斷當前頁數是否正確
    if (pageNumberLong > pages.getPages()) {
      pages = blogService.getBlogs(pages.getPages());
    } else if (pageNumberLong <= 0) {
      pages.setCurrent(1L);
    } else {
      pages.setCurrent(pageNumberLong);
    }
    List<Integer> pageNumbers = PageUtil.pageNumbers(pages);
    model.addAttribute("pages", pages);
    model.addAttribute("pageNumbers", pageNumbers);
    return "admin/blogs";
  }
}
