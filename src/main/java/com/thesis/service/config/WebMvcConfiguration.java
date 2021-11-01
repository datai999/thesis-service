package com.thesis.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins(
            "http://localhost:3000",
            "https://datai999.github.io",
            "https://datai-thesis-web.herokuapp.com",
            "https://datai-thesis-web-dev.herokuapp.com")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
  }

}
