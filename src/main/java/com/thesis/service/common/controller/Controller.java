package com.thesis.service.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Controller {

  @GetMapping
  public Object controller() {
    @Data
    class Version {
      String version = "6.15.16";
    }
    return new Version();
  }

}
