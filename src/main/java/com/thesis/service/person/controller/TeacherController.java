package com.thesis.service.person.controller;

import javax.validation.Valid;
import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.person.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Override
  @PostMapping("/paging/search")
  public Object search(@RequestBody @Valid SearchRequest requestBody) {
    return super.service.search(requestBody);
  }
}
