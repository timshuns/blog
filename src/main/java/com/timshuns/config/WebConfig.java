package com.timshuns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import com.timshuns.interceptor.LoginInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

  //登入功能攔截器
  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new LoginInterceptor())
        .addPathPatterns("/admin/**")
        .excludePathPatterns("/admin/loginPage")
        .excludePathPatterns("/admin/login");
  }

  // 配置默認的靜態資源路徑
  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    super.addResourceHandlers(registry);
  }
}
