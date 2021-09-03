package com.thesis.service.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.thesis.service.utils.ContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Interceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    ContextHolder.setRequest(request);
    log.info("[{}][{}][{}]", ContextHolder.getLang(), request.getMethod(), request.getRequestURL());
    return true;
  }

}
