package com.timshuns.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.mapper.TagMapper;
import com.timshuns.pojo.Tag;
import com.timshuns.service.TagService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TagServiceImpl implements TagService {
  
  @Value("${page.size}")
  private long pageSize;

  @Autowired
  private TagMapper tagMapper;

  @Transactional
  @Override
  public boolean saveTag(Tag tag) {
    // 新增失敗，返回空值
    boolean result = false;
    try {
      result = (tagMapper.insert(tag) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

  @Transactional
  @Override
  public Tag getTag(Long id) {
    return tagMapper.selectById(id);
  }

  @Transactional
  @Override
  public Page<Tag> getTags(long currentPage) {
    Page<Tag> page = new Page<Tag>(currentPage, pageSize);
    tagMapper.selectPage(page, null);
    return page;
  }

  @Transactional
  @Override
  public boolean updateTag(Tag type) {
    // 失敗，返回空值
    boolean result = false;
    try {
      result = (tagMapper.updateById(type) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

  @Transactional
  @Override
  public boolean deleteTag(Long id) {
 // 失敗，返回空值
    boolean result = false;
    try {
      result = (tagMapper.deleteById(id) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

}
