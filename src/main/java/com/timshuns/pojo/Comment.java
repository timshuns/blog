package com.timshuns.pojo;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
  @TableId(type = IdType.AUTO)
  private Long id;
  @TableField(value = "blog_id")
  private Long blogId;
  private String nickName;
  private String email;
  private String content;
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

}
