package com.timshuns.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Tag;

public interface TagService {

  boolean saveTag(Tag tag);

  List<Long> saveTags(List<String> tagNames);

  Tag getTag(Long id);

  Page<Tag> getTags(long currentPage,String tagName);

  List<Tag> getAllTags();
  
  List<Tag> getTagsByBlogId(long blogId);

  boolean updateTag(Tag tag);

  boolean deleteTag(Long id);
}
