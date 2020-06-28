package com.timshuns.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Tag;
import com.timshuns.pojo.Type;

public interface TagService {
  
  boolean saveTag(Tag tag);
  
  List<Long> saveTags(List<String> tagNames);

  Tag getTag(Long id);

  Page<Tag> getTags(long currentPage);
  
  List<Tag> getAllTags();

  boolean updateTag(Tag tag);

  boolean deleteTag(Long id);
}
