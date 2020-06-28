package com.timshuns.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Type;

public interface TypeService {
  boolean saveType(Type type);

  Type getType(Long id);

  Page<Type> getTypes(long currentPage);
  
  List<Type> getAllTypes();

  boolean updateType(Type type);

  boolean deleteType(Long id);
}
