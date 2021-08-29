package com.thesis.service.common.service;

import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Getter;

@Getter
public abstract class AbstractBaseService<T extends BaseTable, R extends BaseRepository<T>> {

  @Autowired
  public R repository;

}
