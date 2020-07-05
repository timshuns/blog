package com.timshuns.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Blog;

public interface BlogService {

  Blog getBlog(Long id);

  Page<Blog> getBlogs(long currentPage,String title,int typeId,int published);

  boolean saveBlog(Blog blog);

  boolean updateBlog(Blog blog);

  boolean deleteBlog(Long id);
}
