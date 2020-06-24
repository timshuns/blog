package com.timshuns.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Type;

public interface TypeService {
  Type saveType(Type type);

  Type getType(Long id);

  Page<Type> getTypes(long currentPage);

  Type updateType(Type type);

  void deleteType(Long id);
}
