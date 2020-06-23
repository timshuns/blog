package com.timshuns.service;

import com.timshuns.pojo.User;

public interface UserService {

  /** 驗證登入資訊 */
  User checkUser(String userName, String password);
}
