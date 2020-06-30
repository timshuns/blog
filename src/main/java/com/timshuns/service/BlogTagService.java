package com.timshuns.service;

import java.util.List;
import com.timshuns.pojo.BlogTag;

public interface BlogTagService {
  boolean saveBlogTag(BlogTag blogTag);
  
  List<Long> selectByBlogId(Long blogId);
}
