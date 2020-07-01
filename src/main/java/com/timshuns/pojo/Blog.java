package com.timshuns.pojo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
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

  @TableField(value = "publish_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date publishTime;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;
}
