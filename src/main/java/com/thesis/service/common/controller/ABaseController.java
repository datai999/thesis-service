package com.thesis.service.common.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.thesis.service.br.ConstDataService;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public abstract class ABaseController<E extends BaseTable, S extends BaseRepository<E> & IService<E>> {

  @Autowired
  protected S service;
  @Autowired
  protected ConstDataService constService;

  @GetMapping
  public List<E> findAll() {
    return service.findAll().stream().map(entity -> service.build(entity)).collect(Collectors.toList());
  }

  @GetMapping("/paging")
  public Object findPaging(@RequestParam @NotNull @PositiveOrZero Integer number,
      @RequestParam @NotNull @Positive Integer size, Optional<String> sort,
      @RequestParam(defaultValue = "false") boolean descend) {

    Pageable pageable = null;

    if (sort.isPresent()) {
      Sort sortable = descend ? Sort.by(sort.get()).descending() : Sort.by(sort.get());
      pageable = PageRequest.of(number, size, sortable);
    } else {
      pageable = PageRequest.of(number, size);
    }

    return service.findAll(pageable).map(entity -> service.build(entity));
  }

  @PostMapping
  public <D extends E> Object save(@RequestBody @Valid D requestBody) {
    return service.build(service.save(requestBody));
  }

  @PostMapping("/all")
  public <D extends E> Object saveAll(@RequestBody List<D> requestBody) {
    return service.saveAll(requestBody);
  }

  @PostMapping("/example")
  public Object findAll(@RequestBody E entity) {
    return service.findAll(Example.of(entity));
  }

  @DeleteMapping
  public Object deleteAll() {
    service.deleteAll();
    return true;
  }

}
