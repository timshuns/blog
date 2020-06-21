package com.timshuns.pojo;

import java.util.Date;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFirstPicure() {
    return firstPicure;
  }

  public void setFirstPicure(String firstPicure) {
    this.firstPicure = firstPicure;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return "Blog [id=" + id + ", title=" + title + ", flag=" + flag + ", views=" + views
        + ", published=" + published + ", createTime=" + createTime + ", updateTime=" + updateTime
        + "]";
  }


}
