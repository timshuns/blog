package com.timshuns.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timshuns.pojo.Blog;

public interface BlogMapper extends BaseMapper<Blog> {

  @Update("update blog set views = (views+1) where id = #{id}")
  void updateViews(@Param("id") long id);
}
