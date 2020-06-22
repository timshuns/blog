package com.timshuns.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

  private Long id;
  private String nickName;
  private String email;
  private String content;
  private Date createTime;

}
