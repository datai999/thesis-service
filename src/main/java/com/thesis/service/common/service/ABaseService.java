package com.thesis.service.common.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.thesis.service.br.repository.BrConstDataRepository;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class ABaseService<T extends BaseTable, R extends BaseRepository<T>>
    implements BaseRepository<T>, IService<T> {

  @Autowired
  protected R mainRepository;

  @Autowired
  protected BrConstDataRepository constRepository;

  protected abstract void preBuild(T entity);

  @Override
  public T build(T entity) {
    if (Objects.nonNull(entity)) {
      preBuild(entity);
    }
    return entity;
  }

  @Override
  public List<T> findAll() {
    return mainRepository.findAll();
  }

  @Override
  public List<T> findAll(Sort sort) {
    return mainRepository.findAll();
  }

  @Override
  public List<T> findAllById(Iterable<Long> ids) {
    return mainRepository.findAllById(ids);
  }

  @Override
  public <S extends T> List<S> saveAll(Iterable<S> entities) {
    return mainRepository.saveAll(entities);
  }

  @Override
  public void flush() {
    mainRepository.flush();
  }

  @Override
  public <S extends T> S saveAndFlush(S entity) {
    return mainRepository.saveAndFlush(entity);
  }

  @Override
  public void deleteInBatch(Iterable<T> entities) {
    mainRepository.deleteInBatch(entities);
  }

  @Override
  public void deleteAllInBatch() {
    mainRepository.deleteAllInBatch();
  }

  @Override
  public T getOne(Long id) {
    return mainRepository.getOne(id);
  }

  @Override
  public <S extends T> List<S> findAll(Example<S> example) {
    return mainRepository.findAll(example);
  }

  @Override
  public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
    return mainRepository.findAll(example, sort);
  }

  @Override
  public Page<T> findAll(Pageable pageable) {
    return mainRepository.findAll(pageable);
  }

  @Override
  public <S extends T> S save(S entity) {
    entity.mapIdOrCode();
    return mainRepository.save(entity);
  }

  @Override
  public Optional<T> findById(Long id) {
    return mainRepository.findById(id);
  }

  @Override
  public boolean existsById(Long id) {
    return mainRepository.existsById(id);
  }

  @Override
  public long count() {
    return mainRepository.count();
  }

  @Override
  public void deleteById(Long id) {
    mainRepository.deleteById(id);
  }

  @Override
  public void delete(T entity) {
    mainRepository.delete(entity);
  }

  @Override
  public void deleteAll(Iterable<? extends T> entities) {
    mainRepository.deleteAll(entities);
  }

  @Override
  public void deleteAll() {
    mainRepository.deleteAll();
  }

  @Override
  public <S extends T> Optional<S> findOne(Example<S> example) {
    return mainRepository.findOne(example);
  }

  @Override
  public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
    return mainRepository.findAll(example, pageable);
  }

  @Override
  public <S extends T> long count(Example<S> example) {
    return mainRepository.count(example);
  }

  @Override
  public <S extends T> boolean exists(Example<S> example) {
    return mainRepository.exists(example);
  }

}
