package com.timshuns.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTag {
  @TableField(value = "blog_id")
  private Long blogId;
  @TableField(value = "tag_id")
  private Long tagId;
}
