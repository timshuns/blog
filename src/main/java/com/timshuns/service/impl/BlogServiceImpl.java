package com.timshuns.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.mapper.BlogMapper;
import com.timshuns.pojo.Blog;
import com.timshuns.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

  @Autowired
  private BlogMapper blogMapper;

  @Override
  public Blog getBlog(Long id) {
    return blogMapper.selectById(id);
  }

  @Override
  public Page<Blog> getBlogs(long currentPage) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean saveBlog(Blog blog) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean updateBlog(Blog blog) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean deleteBlog(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

}
