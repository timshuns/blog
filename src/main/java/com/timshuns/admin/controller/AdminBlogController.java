package com.timshuns.admin.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Blog;
import com.timshuns.pojo.BlogTag;
import com.timshuns.pojo.Tag;
import com.timshuns.pojo.Type;
import com.timshuns.service.BlogService;
import com.timshuns.service.BlogTagService;
import com.timshuns.service.TagService;
import com.timshuns.service.TypeService;
import com.timshuns.util.PageUtil;

@Controller
@RequestMapping("/admin/blogs")
public class AdminBlogController {

  @Autowired private BlogService blogService;

  @Autowired private TypeService typeService;

  @Autowired private TagService tagService;

  @Autowired private BlogTagService blogTagService;

  @GetMapping("/createPage")
  public String readBlogCreatePage(Model model) {
    // 取得類別
    List<Type> types = typeService.getAllTypes();
    List<Tag> tags = tagService.getAllTags();

    model.addAttribute("tags", tags);
    model.addAttribute("types", types);

    return "/admin/blogs-create";
  }

  @GetMapping("/updatePage/{id}")
  public String readBlogUpdatePage(Model model, @PathVariable Long id) {
    // 取得類別
    List<Type> types = typeService.getAllTypes();
    List<Tag> tags = tagService.getAllTags();

    List<Long> tagIds = blogTagService.selectByBlogId(id);

    Blog blog = blogService.getBlog(id);

    model.addAttribute("types", types);
    model.addAttribute("tags", tags);
    model.addAttribute("blog", blog);
    model.addAttribute("tagIds", tagIds);

    return "/admin/blogs-create";
  }

  /** 文章起始頁面 */
  @GetMapping("/index")
  public String readBlogPage(
      Model model, @RequestParam(value = "pageNumber", required = false) String pageNumber,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "typeId", required = false ,defaultValue = "-1") int typeId,
      @RequestParam(value = "published", required = false,defaultValue = "-1") int published) {
    // 參數判斷
    Long pageNumberLong = 1L;

    try {
      pageNumberLong = Long.valueOf(pageNumber);
    } catch (NumberFormatException e) {
      // 轉換失敗，改用預設值
    }

    Page<Blog> pages = blogService.getBlogs(pageNumberLong,title,typeId,published);

    // 查無資料
    if (pages == null) {
      model.addAttribute("pages", null);
      return "admin/blogs";
    }

    // 判斷當前頁數是否正確
    if (pageNumberLong > pages.getPages()) {
      pages = blogService.getBlogs(pages.getPages(),title,typeId,published);
    } else if (pageNumberLong <= 0) {
      pages.setCurrent(1L);
    } else {
      pages.setCurrent(pageNumberLong);
    }

    List<Type> types = typeService.getAllTypes();
    Map<Long, String> typesMap = new HashMap<Long, String>();
    for (Type type : types) {
      typesMap.put(type.getId(), type.getName());
    }
    model.addAttribute("typesMap", typesMap);
    List<Integer> pageNumbers = PageUtil.pageNumbers(pages);
    model.addAttribute("title", title);
    model.addAttribute("typeId", typeId);
    model.addAttribute("published", published);
    model.addAttribute("pages", pages);
    model.addAttribute("pageNumbers", pageNumbers);
    return "admin/blogs";
  }

  /** 新增 */
  @PostMapping("/save")
  public String save(
      Model model,
      RedirectAttributes attributes,
      Blog blog,
      @RequestParam(value = "tags", required = false) List<String> tagNames,
      @RequestParam(value = "type", required = true) String type) {
    // 處理 tag
    List<Long> tagResult = tagService.saveTags(tagNames);

    // 處理 type
    if (type.startsWith("@")) {
      // 尚未存檔
      Type tempType = new Type();
      tempType.setName(type.substring(1));
      tempType.setStatus(1);
      typeService.saveType(tempType);
      blog.setTypeId(tempType.getId());
    } else {
      blog.setTypeId(Long.valueOf(type));
    }

    // 儲存 blog
    blogService.saveBlog(blog);

    // 儲存 blog tag
    for (Long tagId : tagResult) {
      BlogTag blogTag = new BlogTag();
      blogTag.setBlogId(blog.getId());
      blogTag.setTagId(tagId);
      blogTagService.saveBlogTag(blogTag);
    }

    return "redirect:/admin/blogs/index.html";
  }

  /** 新增 */
  @PostMapping("/update/{id}")
  public String update(
      Model model,
      RedirectAttributes attributes,
      Blog blog,
      @RequestParam(value = "tags", required = false) List<String> tagNames,
      @RequestParam(value = "type", required = true) String type,
      @PathVariable Long id) {
    // 處理 tag
    List<Long> tagResult = tagService.saveTags(tagNames);

    // 處理 type
    if (type.startsWith("@")) {
      // 尚未存檔
      Type tempType = new Type();
      tempType.setName(type.substring(1));
      tempType.setStatus(1);
      Long typdId = typeService.saveType(tempType);
      blog.setTypeId(typdId);
    } else {
      blog.setTypeId(Long.valueOf(type));
    }

    // 若未設置發佈時間，將時間設為最大值
    if (blog.getPublishTime() == null) {
      LocalDateTime publishTime = LocalDateTime.now();
      publishTime.plusYears(100);
      blog.setPublishTime(Date.from(publishTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    // 儲存 blog
    blog.setId(id);
    blogService.updateBlog(blog);

    // 儲存 blog tag 前，先刪除原有紀錄
    blogTagService.deleteByBlogId(blog.getId());
    for (Long tagId : tagResult) {
      BlogTag blogTag = new BlogTag();
      blogTag.setBlogId(blog.getId());
      blogTag.setTagId(tagId);
      blogTagService.saveBlogTag(blogTag);
    }

    return "redirect:/admin/blogs/index.html";
  }
}
