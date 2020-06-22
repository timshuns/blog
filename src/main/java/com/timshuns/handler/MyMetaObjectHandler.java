package com.timshuns.handler;

import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

  // 新增資料 生成策略
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("createTime", new Date(), metaObject);
  }

  // 更新資料 生成策略
  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateTime", new Date(), metaObject);
  }

}
