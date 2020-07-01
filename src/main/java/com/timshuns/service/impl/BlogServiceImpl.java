package com.timshuns.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

  @Autowired private BlogMapper blogMapper;

  @Override
  public Blog getBlog(Long id) {
    return blogMapper.selectById(id);
  }

  @Override
  public Page<Blog> getBlogs(long currentPage) {
    Page<Blog> page = new Page<Blog>(currentPage, pageSize);
    blogMapper.selectPage(page, null);
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
}
