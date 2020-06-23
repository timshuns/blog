package com.timshuns.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.mapper.TypeMapper;
import com.timshuns.pojo.Type;
import com.timshuns.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {
  private static final long pageSize = 10;

  @Autowired private TypeMapper typeMapper;

  @Transactional
  @Override
  public Type saveType(Type type) {
    typeMapper.insert(type);
    return type;
  }

  @Transactional
  @Override
  public Type getType(Long id) {
    return typeMapper.selectById(id);
  }

  @Transactional
  @Override
  public List<Type> getTypesWithPage(long currentPage) {
    Page<Type> page = new Page<Type>(currentPage, pageSize);
    typeMapper.selectPage(page, null);
    return page.getRecords();
  }

  @Transactional
  @Override
  public Type updateType(Type type) {
    int result = typeMapper.updateById(type);
    if (result == 0) {
      return null;
    }
    return type;
  }

  @Transactional
  @Override
  public void deleteType(Long id) {
    typeMapper.deleteById(id);
  }
}
