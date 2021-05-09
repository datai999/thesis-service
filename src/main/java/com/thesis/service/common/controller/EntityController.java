package com.thesis.service.common.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.thesis.service.br.repository.BrConstDataRepository;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.EntityService;

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
public abstract class EntityController<E extends BaseTable, R extends BaseRepository<E>> {

  @Autowired
  private EntityService service;

  protected R repository;
  protected BrConstDataRepository constRepository;

  public abstract String declareBaseService();

  @PostConstruct
  protected void setBaseService() {
    this.repository = this.service.get(this.declareBaseService());
    this.constRepository = this.service.get("constData");
  }

  @GetMapping
  public Object findAll() {
    return repository.findAll();
  }

  @GetMapping("/paging")
  public Object findPaging(@RequestParam @NotNull @PositiveOrZero Integer page,
      @RequestParam @NotNull @Positive Integer size, Optional<String> sort,
      @RequestParam(defaultValue = "false") boolean descend) {

    Pageable pageable = null;

    if (sort.isPresent()) {
      Sort sortable = descend ? Sort.by(sort.get()).descending() : Sort.by(sort.get());
      pageable = PageRequest.of(page, size, sortable);
    } else {
      pageable = PageRequest.of(page, size);
    }

    return repository.findAll(pageable);
  }

  @PostMapping
  public <D extends E> Object save(@RequestBody D requestBody) {
    return repository.save(requestBody);
  }

  @PostMapping("/all")
  public <D extends E> Object saveAll(@RequestBody List<D> requestBody) {
    return repository.saveAll(requestBody);
  }

  @PostMapping("/example")
  public Object findAll(@RequestBody E entity) {
    return repository.findAll(Example.of(entity));
  }

  @DeleteMapping
  public Object deleteAll() {
    repository.deleteAll();
    return true;
  }

}
