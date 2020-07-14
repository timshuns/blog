package com.timshuns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Blog;
import com.timshuns.pojo.Tag;
import com.timshuns.pojo.Type;
import com.timshuns.service.BlogService;
import com.timshuns.service.TagService;
import com.timshuns.service.TypeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogsController {

  @Autowired private TypeService typeService;

  @Autowired private BlogService blogService;

  @Autowired private TagService tagService;

  @GetMapping({"/blog.html"})
  public String index(
      Model model,
      @RequestParam(value = "typeId", required = false, defaultValue = "0") long typeId,
      @RequestParam(value = "pageNumber", required = false) String pageNumber) {
    log.info("blog");

    List<Type> types = typeService.getTypesWithEnable();

    Map<Long, String> typesMap = new HashMap<Long, String>();
    for (Type type : types) {
      typesMap.put(type.getId(), type.getName());
    }

    // 參數判斷
    Long currentPage = 1L;

    try {
      currentPage = Long.valueOf(pageNumber);
    } catch (NumberFormatException e) {
      // 轉換失敗，改用預設值
    }

    Page<Blog> blogs = blogService.getBlogs(currentPage, "", typeId, 1);

    // 查詢標籤
    Map<String, List<Tag>> blogTags = new HashMap<String, List<Tag>>();
    for (Blog blog : blogs.getRecords()) {
      blogTags.put(String.valueOf(blog.getId()), tagService.getTagsByBlogId(blog.getId()));
    }

    model.addAttribute("typesMap", typesMap);
    model.addAttribute("blogTags", blogTags);
    model.addAttribute("types", types);
    model.addAttribute("blogs", blogs);
    return "blog";
  }

  @GetMapping({"/blog/{id}"})
  public String detail(Model model, @PathVariable long id) {
    log.info("blog-detail");

    Blog blog = blogService.getBlog(id);
    List<Tag> tags = tagService.getTagsByBlogId(id);

    model.addAttribute("blog", blog);
    model.addAttribute("tags", tags);
    return "blog-detail";
  }
  
  @PostMapping({"/comment/{id}"})
  public String comment(Model model, @PathVariable long id) {
    log.info("blog-detail");

    Blog blog = blogService.getBlog(id);
    List<Tag> tags = tagService.getTagsByBlogId(id);

    model.addAttribute("blog", blog);
    model.addAttribute("tags", tags);
    return "blog-detail";
  }
}
