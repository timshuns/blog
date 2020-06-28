package com.timshuns.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timshuns.interceptor.LoginInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

  // 登入功能攔截器
  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")
        .excludePathPatterns("/admin/loginPage").excludePathPatterns("/admin/login");
  }

  // 配置默認的靜態資源路徑
  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    super.addResourceHandlers(registry);
  }

  // 解決 put,delete method 後端接收不到參數
  @Bean
  public FormContentFilter formContentFilter() {
    return new FormContentFilter();
  }

  // 解決回傳中文亂碼
  @Bean
  public HttpMessageConverter<String> responseBodyStringConverter() {
    StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
    return converter;
  }

  @Bean
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public MappingJackson2HttpMessageConverter messageConverter() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(getObjectMapper());
    return converter;
  }

  /**
   * 修改StringHttpMessageConverter默認配置
   * 
   * @param converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.configureMessageConverters(converters);
    // 解决中文亂碼
    converters.add(messageConverter());
    converters.add(responseBodyStringConverter());
  }

}
