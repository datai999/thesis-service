package com.thesis.service.service;

import java.lang.reflect.ParameterizedType;
import com.thesis.service.dto.ModelConverter;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractBaseService<T extends BaseTable, R extends BaseRepository<T>> {

  @Autowired
  protected ModelConverter mapper;

  @Autowired
  protected R repository;

  public UserTable getAuth() {
    return UserTable.class.cast(
        SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }

  @SuppressWarnings("unchecked")
  protected Class<?> getResponseClass() {
    var entityType = ParameterizedType.class
        .cast(this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[0];
    return (Class<T>) entityType;
  }

  public Object findAll(Sort sort) {
    return this.mapper.map(this.repository.findAll(sort), this.getResponseClass());
  }

  public Object findById(Long id) {
    return this.mapper.map(this.repository.findById(id), this.getResponseClass());
  }

  public Object findByExample(T entity, Sort sort) {
    var example = Example.of(entity);
    var response = this.repository.findAll(example, sort);
    return this.mapper.map(response, this.getResponseClass());
  }

  public Object save(T entity) {
    this.repository.save(entity);
    return true;
  }

}
