package com.thesis.service.controller;

import com.thesis.service.model.BaseTable;
import com.thesis.service.service.ABaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public abstract class ABaseController<T extends BaseTable, S extends ABaseService<T, ?>> {

  @Autowired
  protected S service;

  @GetMapping
  public Object findByDeletedFalse(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.findByDeletedFalse(sortable);
  }

  @GetMapping("/all")
  public Object findAll(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.findAll(sortable);
  }

  @GetMapping("/detail/{id}")
  public Object findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @PostMapping("/example")
  public Object findByExample(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort,
      @RequestBody T entity) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.findByExample(entity, sortable);
  }

  @PostMapping
  public Object create(@RequestBody T entity) {
    return service.create(entity);
  }

  @PatchMapping
  public Object update(@RequestBody T entity) {
    return service.update(entity);
  }

  @DeleteMapping("/{id}")
  public Object deleteById(@PathVariable Long id) {
    return service.deleteById(id);
  }

}
