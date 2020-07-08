package com.timshuns.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.timshuns.pojo.Type;

public interface TypeService {
  Long saveType(Type type);

  Type getType(Long id);

  Page<Type> getTypes(long currentPage, String typeName,int typeStatus);

  List<Type> getAllTypes();
  
  List<Type> getTypesWithEnable();

  boolean updateType(Type type);

  boolean deleteType(Long id);
}
