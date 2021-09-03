package com.thesis.service.service;

import com.thesis.service.model.BaseTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;

@Getter
public abstract class AbstractBaseService<T extends BaseTable, R extends BaseRepository<T>> {

  @Autowired
  public R repository;

}
