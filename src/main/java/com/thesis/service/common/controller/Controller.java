package com.thesis.service.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  @GetMapping("/")
  public String controller() {
    return "Version:5.09.10";
  }
}
