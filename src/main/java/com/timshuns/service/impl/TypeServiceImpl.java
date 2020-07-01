package com.timshuns.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

  @Autowired private TypeMapper typeMapper;

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
    System.err.println("type:"+type.getId() ); 
    return result ? type.getId() : 0;
  }

  @Transactional
  @Override
  public Type getType(Long id) {
    return typeMapper.selectById(id);
  }

  @Transactional
  @Override
  public Page<Type> getTypes(long currentPage) {
    Page<Type> page = new Page<Type>(currentPage, pageSize);
    // page.setSize(1);
    typeMapper.selectPage(page, null);
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
