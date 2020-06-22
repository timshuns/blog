package com.timshuns.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // 設置攔截器，判斷是否為登入狀態
    if (request.getSession().getAttribute("user") == null) {
      System.err.println("登入判斷");
      response.sendRedirect("/index");
      return false;
    }else {
      System.err.println("ok");
    }
    return true;
  }

}
