package com.timshuns.service.impl;

import java.security.KeyStore.Entry;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.mapper.TypeMapper;
import com.timshuns.pojo.Type;
import com.timshuns.service.TypeService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

  @Value("${page.size}")
  private long pageSize;

  @Autowired
  private TypeMapper typeMapper;

  @Transactional
  @Override
  public Long saveType(Type type) {
    // 新增失敗，返回空值
    boolean result = false;
    try {
      result = (typeMapper.insert(type) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    System.err.println("type:" + type.getId());
    return result ? type.getId() : 0;
  }

  @Transactional
  @Override
  public Type getType(Long id) {
    return typeMapper.selectById(id);
  }

  @Transactional
  @Override
  public Page<Type> getTypes(long currentPage, String typeName, int typeStatus) {
    QueryWrapper<Type> queryWrapper = new QueryWrapper<Type>();
    Page<Type> page = new Page<Type>(currentPage, pageSize);

    if(!StringUtils.isBlank(typeName)) {
      queryWrapper.like("name", typeName);
    }
    if(typeStatus >=0) {
      queryWrapper.eq("status", typeStatus);
    }

    typeMapper.selectPage(page, queryWrapper);
    return page;
  }

  @Transactional
  @Override
  public boolean updateType(Type type) {
    // 失敗，返回空值
    boolean result = false;
    try {
      result = (typeMapper.updateById(type) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

  @Transactional
  @Override
  public boolean deleteType(Long id) {
    // 失敗，返回空值
    boolean result = false;
    try {
      result = (typeMapper.deleteById(id) > 0);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return result;
  }

  @Override
  public List<Type> getAllTypes() {
    return typeMapper.selectList(null);
  }
}
