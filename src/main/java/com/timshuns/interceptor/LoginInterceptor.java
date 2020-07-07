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
      response.sendRedirect("/blog/index");
      return false;
    }
    return true;
  }

}
