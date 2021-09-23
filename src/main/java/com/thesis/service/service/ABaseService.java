package com.thesis.service.service;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;
import java.util.function.Function;
import com.thesis.service.dto.ModelConverter;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class ABaseService<T extends BaseTable, R extends BaseRepository<T>> {

  @Autowired
  protected ModelConverter mapper;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  protected R repository;

  protected String getMessage(String code, Object... args) {
    return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
  }

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

  protected Function<T, ?> mapping() {
    return null;
  }

  public Object findAll(Sort sort) {
    var response = this.repository.findAll(sort);
    return Objects.isNull(this.mapping())
        ? this.mapper.map(response, this.getResponseClass())
        : this.mapper.map(response, this.mapping());
  }

  public Object findById(Long id) {
    var response = this.repository.findById(id).orElseThrow();
    return Objects.isNull(this.mapping())
        ? this.mapper.map(response, this.getResponseClass())
        : this.mapping().apply(response);
  }

  public Object findByExample(T entity, Sort sort) {
    entity.setCreatedAt(null).setUpdatedAt(null);
    var example = Example.of(entity);
    var response = this.repository.findAll(example, sort);
    return Objects.isNull(this.mapping())
        ? this.mapper.map(response, this.getResponseClass())
        : this.mapper.map(response, this.mapping());
  }

  public Object save(T entity) {
    return this.repository.save(entity).getId();
  }

  public Object create(T entity) {
    return this.repository.save(entity).getId();
  }

  public Object update(T entity) {
    this.repository.findById(entity.getId()).orElseThrow();
    return this.repository.save(entity).getId();
  }

  public Object deleteById(Long id) {
    this.repository.deleteById(id);
    return true;
  }

}
