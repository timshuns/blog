package com.timshuns.service;

import com.timshuns.pojo.User;

public interface UserService {

  User checkUser(String userName,String password);
}
