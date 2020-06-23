package com.timshuns.service;

import java.util.List;
import com.timshuns.pojo.Type;

public interface TypeService {
  Type saveType(Type type);

  Type getType(Long id);

  List<Type> getTypesWithPage(long currentPage);

  Type updateType(Type type);

  void deleteType(Long id);
}
