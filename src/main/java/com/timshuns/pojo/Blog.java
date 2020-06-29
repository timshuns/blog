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
public class Blog {
  @TableId(type = IdType.AUTO)
  private Long id;
  /** 標題 */
  private String title;
  /** 內文 */
  private String content;
  /** 圖片 */
  private String cover;
  /** 類型 */
  @TableField(value = "type_id")
  private Long typeId;
  /** 觀看次數 */
  private Integer views;
  /** 是否發布 */
  private boolean published;
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;

}
