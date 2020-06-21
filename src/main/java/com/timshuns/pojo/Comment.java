package com.timshuns.pojo;

import java.util.Date;

public class Comment {

  private Long id;
  private String nickName;
  private String email;
  private String content;
  private Date createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "Comment [id=" + id + ", nickName=" + nickName + ", email=" + email + ", content="
        + content + ", createTime=" + createTime + "]";
  }


}
