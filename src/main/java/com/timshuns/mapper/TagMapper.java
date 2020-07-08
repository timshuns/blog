package com.timshuns.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timshuns.pojo.Tag;

public interface TagMapper extends BaseMapper<Tag> {

  @Select("select * from tag where id in (select tag_id from blog_tag where blog_id = #{blog_id})")
  List<Tag> selectByBlogId(@Param("blog_id") long blogId);
}
