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
  private String title;
  private String content;
  private String firstPicure;
  private String flag;
  private Integer views;
  private boolean published;
  private Date createTime;
  private Date updateTime;

}
