package com.thesis.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class BeanRegister {

  // @Bean
  public CommonsRequestLoggingFilter logFilter() {
    var filter = new CommonsRequestLoggingFilter();
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(10000);
    filter.setIncludeClientInfo(true);
    return filter;
  }

}
