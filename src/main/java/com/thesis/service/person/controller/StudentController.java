package com.thesis.service.person.controller;

import javax.validation.Valid;
import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.person.model.PsStudentTable;
import com.thesis.service.person.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController extends ABaseController<PsStudentTable, StudentService> {

  @GetMapping("search")
  public Object search(String value) {
    return super.service.searchIlikeNameOrCode(value);
  }

  @Override
  @PostMapping("/paging/search")
  public Object search(@RequestBody @Valid SearchRequest requestBody) {
    return super.service.search(requestBody);
  }
}
