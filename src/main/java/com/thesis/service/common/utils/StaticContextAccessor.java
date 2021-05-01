package com.thesis.service.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class StaticContextAccessor implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    synchronized (this) {
      if (StaticContextAccessor.applicationContext == null) {
        StaticContextAccessor.applicationContext = applicationContext;
      }
    }

  }

  public static <T> T getBean(Class<T> clazz) {
    return applicationContext.getBean(clazz);
  }

  public static <T> T getBean(String qualifier, Class<T> clazz) {
    return applicationContext.getBean(qualifier, clazz);
  }

}
