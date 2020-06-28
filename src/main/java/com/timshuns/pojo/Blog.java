package com.timshuns.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

  private Long id;
  /** 標題 */
  private String title;
  /** 內文 */
  private String content;
  /** 圖片 */
  private String firstPicture;
  /** 類型 */
  private String type;
  /** 觀看次數 */
  private Integer views;
  /** 是否發布 */
  private boolean published;
  private Date createTime;
  private Date updateTime;

}
