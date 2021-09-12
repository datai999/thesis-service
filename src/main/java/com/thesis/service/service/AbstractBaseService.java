package com.thesis.service.service;

import com.thesis.service.model.BaseTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.BaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.Getter;

@Getter
public abstract class AbstractBaseService<T extends BaseTable, R extends BaseRepository<T>> {

  @Autowired
  protected ModelMapper mapper;

  @Autowired
  protected R repository;

  public UserTable getAuth() {
    return UserTable.class.cast(
        SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }

}
