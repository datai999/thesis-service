package com.thesis.service.common.controller;

import javax.annotation.PostConstruct;

import com.thesis.service.common.dto.response.WrapResponse;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class EntityController<E extends BaseTable> {

  @Autowired
  private EntityService service;

  private BaseRepository<E> repository;

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
  public Object save(@RequestBody E requestBody) {
    return WrapResponse.data(repository.save(requestBody));
  }

  @PostMapping("/all")
  public Object saveAll(@RequestBody Iterable<E> requestBody) {
    return WrapResponse.data(repository.saveAll(requestBody));
  }

}
