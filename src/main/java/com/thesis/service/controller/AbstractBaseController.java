package com.thesis.service.controller;

import com.thesis.service.model.BaseTable;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public abstract class AbstractBaseController<T extends BaseTable, S extends AbstractBaseService<T, ?>> {

  @Autowired
  protected S service;

  @GetMapping
  public Object findAll(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.findAll(sortable);
  }

  @GetMapping("/{id}/detail")
  public Object findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping
  public Object save(T entity) {
    return service.save(entity);
  }

}
