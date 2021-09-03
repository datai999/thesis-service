package com.thesis.service.controller;

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
      String version = "7.06.01";
    }
    return new Version();
  }

}
