package com.timshuns.service.impl;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.timshuns.mapper.UserMapper;
import com.timshuns.pojo.User;
import com.timshuns.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public User checkUser(String userName, String password) {
    Map<String, Object> queryMap = new HashMap<String, Object>();
    queryMap.put("user_name", userName);
    queryMap.put("password", getMd5Result(password));
    List<User> userList = userMapper.selectByMap(queryMap);
    if (userList.size() > 0) {
      return userList.get(0);
    }
    return null;
  }

  private String getMd5Result(String str) {
    String ret = null;
    try {
      // 生成一個MD5加密計算摘要
      MessageDigest md = MessageDigest.getInstance("MD5");
      // 計算md5函數
      md.update(str.getBytes());
      // digest()最後確定返回md5 hash值，返回值為8為字符串。因為md5 hash值是16位的hex值，實際上就是8位的字符
      // BigInteger函數則將8位的字符串轉換成16位hex值，用字符串來表示；得到字符串形式的hash值
      ret = new BigInteger(1, md.digest()).toString(16);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.err.println(ret.toUpperCase());
    return ret.toUpperCase();
  }
}
