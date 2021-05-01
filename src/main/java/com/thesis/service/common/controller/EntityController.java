package com.thesis.service.common.controller;

import javax.annotation.PostConstruct;

import com.thesis.service.common.dto.response.WrapResponse;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class EntityController<E extends BaseTable, R extends BaseRepository<E>> {

  @Autowired
  private EntityService service;

  protected R repository;

  public abstract String declareBaseService();

  @PostConstruct
  protected void setBaseService() {
    this.repository = this.service.get(this.declareBaseService());
  }

  @GetMapping
  public Object findAll() {
    return WrapResponse.data(repository.findAll());
  }

  @PostMapping
  public <D extends E> Object save(@RequestBody D requestBody) {
    return WrapResponse.data(repository.save(requestBody));
  }

  @PostMapping("/all")
  public Object saveAll(@RequestBody Iterable<E> requestBody) {
    return WrapResponse.data(repository.saveAll(requestBody));
  }

  @PostMapping("/example")
  protected Object findAll(@RequestBody E entity) {
    return WrapResponse.data(repository.findAll(Example.of(entity)));
  }

}
