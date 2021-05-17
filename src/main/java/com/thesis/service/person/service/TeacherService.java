package com.thesis.service.person.service;

import java.util.List;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.person.repository.PsTeacherRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class TeacherService extends ABaseService<PsTeacherTable, PsTeacherRepository> implements PsTeacherRepository {

  @Override
  public List<PsTeacherTable> searchIlikeName(String value) {
    return super.mainRepository.searchIlikeName(value);
  }

}
