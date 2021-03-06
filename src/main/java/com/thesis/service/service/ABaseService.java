package com.thesis.service.service;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import com.thesis.service.dto.ModelConverter;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.dto.user.CustomUserDetail;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class ABaseService<T extends BaseTable, R extends BaseRepository<T>> {

  @Autowired
  protected ModelConverter mapper;

  @Autowired
  protected MessageSourceService messageSource;

  @Autowired
  protected R repository;

  public UserTable getAuth() {
    var userDetail = CustomUserDetail.class.cast(
        SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return userDetail.getUser();
  }

  protected Class<?> getResponseClass() {
    return BaseResponse.class;
  }

  public Function<T, Object> mapping() {
    return null;
  }

  public Object map(Collection<T> resource) {
    return Objects.isNull(this.mapping())
        ? this.mapper.map(resource, this.getResponseClass())
        : this.mapper.map(resource, this.mapping());
  }

  public Object map(T resource) {
    return Objects.isNull(this.mapping())
        ? this.mapper.map(resource, this.getResponseClass())
        : this.mapping().apply(resource);
  }

  public Object findAll(Sort sort) {
    var response = this.repository.findAll(sort);
    return this.map(response);
  }

  public Object findByDeletedFalse(Sort sort) {
    var response = this.repository.findByDeletedFalse(sort);
    return this.map(response);
  }

  public Object findById(long id) {
    var response = this.repository.findById(id).orElseThrow();
    return this.map(response);
  }

  public Object findByExample(T entity, Sort sort) {
    entity.setCreatedAt(null).setUpdatedAt(null);
    var example = Example.of(entity);
    var response = this.repository.findAll(example, sort);
    return this.map(response);
  }

  public Object save(T entity) {
    return this.repository.save(entity).getId();
  }

  public Object create(T entity) {
    return this.map(this.repository.save(entity));
  }

  public Object saveAll(Collection<T> entities) {
    var response = this.repository.saveAll(entities);
    return this.map(response);
  }

  public Object update(T entity) {
    var existEntity = this.repository.findById(entity.getId()).orElseThrow();
    entity.setCreatedAt(existEntity.getCreatedAt());
    var response = this.repository.save(entity);
    return this.map(response);
  }

  public Object deleteById(Long id) {
    this.repository.deleteById(id);
    return true;
  }

  public Object deleteLogicById(long id) {
    this.repository.deleteLogic(id);
    return true;
  }

}
