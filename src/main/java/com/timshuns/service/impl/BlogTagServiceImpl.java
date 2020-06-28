package com.timshuns.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.timshuns.mapper.BlogMapper;
import com.timshuns.mapper.BlogTagMapper;
import com.timshuns.pojo.BlogTag;
import com.timshuns.service.BlogTagService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogTagServiceImpl implements BlogTagService {

  @Autowired
  private BlogTagMapper blogTagMapper;

  @Override
  public boolean saveBlogTag(BlogTag blogTag) {
    // 新增失敗，返回空值
    boolean result = false;
    try {
      result = (blogTagMapper.insert(blogTag) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

}
