package com.thesis.service.controller.system;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.service.system.EducationMethodService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/education-methods")
@RequiredArgsConstructor
public class EducationMethodController
    extends
    AbstractBaseController<EducationMethodTable, EducationMethodService> {

  @GetMapping
  public Object find(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return super.service.findAll(sortable);
  }

}
