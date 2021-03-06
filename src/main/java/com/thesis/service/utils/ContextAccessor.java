package com.thesis.service.utils;

import com.thesis.service.dto.ModelConverter;
import com.thesis.service.service.MessageSourceService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextAccessor implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    synchronized (this) {
      if (ContextAccessor.applicationContext == null) {
        ContextAccessor.applicationContext = applicationContext;
      }
    }
  }

  public static <T> T getBean(Class<T> clazz) {
    return applicationContext.getBean(clazz);
  }

  public static <T> T getBean(Class<T> clazz, String qualifier) {
    return applicationContext.getBean(qualifier, clazz);
  }

  public static ModelConverter getModelConverter() {
    return getBean(ModelConverter.class);
  }

  public static MessageSourceService getMessageSource() {
    return getBean(MessageSourceService.class);
  }

}
