package com.timshuns.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Tag;

public interface TagService {
  
  boolean saveTag(Tag tag);

  Tag getTag(Long id);

  Page<Tag> getTags(long currentPage);

  boolean updateTag(Tag tag);

  boolean deleteTag(Long id);
}
