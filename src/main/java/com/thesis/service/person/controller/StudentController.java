package com.thesis.service.person.controller;

import com.thesis.service.common.controller.EntityController;
import com.thesis.service.person.model.PsStudentTable;
import com.thesis.service.person.repository.PsStudentRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController extends EntityController<PsStudentTable, PsStudentRepository> {

  @Override
  public String declareBaseService() {
    return "student";
  }

  @GetMapping("search")
  public Object search(String value) {
    return super.repository.searchIlikeNameOrCode(value);
  }

}
