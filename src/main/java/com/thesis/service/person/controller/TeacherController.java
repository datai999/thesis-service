package com.thesis.service.person.controller;

import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.person.service.TeacherService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController extends ABaseController<PsTeacherTable, TeacherService> {

  @GetMapping("search")
  public Object search(String value) {
    return super.service.searchIlikeName(value);
  }

}
