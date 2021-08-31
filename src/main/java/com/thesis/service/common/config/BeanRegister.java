package com.thesis.service.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanRegister {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
