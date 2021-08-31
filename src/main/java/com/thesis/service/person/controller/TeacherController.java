package com.thesis.service.person.controller;

import java.util.stream.Collectors;
import com.thesis.service.common.controller.AbstractBaseController;
import com.thesis.service.person.dto.response.TeacherFlatResponse;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.person.repository.PsTeacherRepository;
import com.thesis.service.person.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController
    extends AbstractBaseController<PsTeacherTable, PsTeacherRepository, TeacherService> {

  @GetMapping("/flat")
  public Object reduce() {
    return service.getRepository().findAll().stream()
        .map(TeacherFlatResponse::from).collect(Collectors.toList());
  }

  @GetMapping("search")
  public Object search(String value) {
    return super.service.getRepository().searchIlikeName(value);
  }

}
