package com.timshuns.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.timshuns.mapper.BlogTagMapper;
import com.timshuns.pojo.BlogTag;
import com.timshuns.service.BlogTagService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogTagServiceImpl implements BlogTagService {

  @Autowired private BlogTagMapper blogTagMapper;

  @Transactional
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

  @Override
  public List<Long> selectByBlogId(Long blogId) {
    List<Long> result = new ArrayList<Long>();
    Map<String, Object> queryMap = new HashMap<String, Object>();
    queryMap.put("blog_id", blogId);
    List<BlogTag> blogTags = blogTagMapper.selectByMap(queryMap);
    for (BlogTag blogTag : blogTags) {
      result.add(blogTag.getTagId());
    }
    return result;
  }

  @Transactional
  @Override
  public void deleteByBlogId(Long blogId) {
    Map<String, Object> queryMap = new HashMap<String, Object>();
    queryMap.put("blog_id", blogId);
    blogTagMapper.deleteByMap(queryMap);
  }
}
