package com.thesis.service.service;

import com.thesis.service.model.BaseTable;
import com.thesis.service.model.person.PersonTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.Getter;

@Getter
public abstract class AbstractBaseService<T extends BaseTable, R extends BaseRepository<T>> {

  @Autowired
  protected R repository;

  public PersonTable getAuth() {
    return PersonTable.class.cast(
        SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }

}
