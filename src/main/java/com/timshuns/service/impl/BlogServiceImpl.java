package com.timshuns.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.mapper.BlogMapper;
import com.timshuns.pojo.Blog;
import com.timshuns.service.BlogService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

  @Value("${page.size}")
  private long pageSize;

  @Autowired
  private BlogMapper blogMapper;

  @Override
  public Blog getBlog(Long id) {
    return blogMapper.selectById(id);
  }

  @Override
  public Page<Blog> getBlogs(long currentPage, String title, long typeId, int published) {
    QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>();
    Page<Blog> page = new Page<Blog>(currentPage, pageSize);

    if (!StringUtils.isBlank(title)) {
      queryWrapper.like("title", title);
    }
    if (typeId > 0) {
      queryWrapper.eq("type_id", typeId);
    }
    if (published >= 0) {
      queryWrapper.eq("published", published);
    }
    queryWrapper.orderByDesc("publish_time");

    blogMapper.selectPage(page, queryWrapper);
    return page;
  }

  @Transactional
  @Override
  public boolean saveBlog(Blog blog) {
    // 新增失敗，返回空值
    boolean result = false;
    try {
      result = (blogMapper.insert(blog) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

  @Override
  public boolean updateBlog(Blog blog) {
    // 更新失敗，返回空值
    boolean result = false;
    try {
      result = (blogMapper.updateById(blog) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

  @Override
  public boolean deleteBlog(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void updateViews(Long id) {
     blogMapper.updateViews(id);
  }
}
