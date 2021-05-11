package com.thesis.service.person.service;

import java.util.List;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.common.service.IService;
import com.thesis.service.person.model.PsStudentTable;
import com.thesis.service.person.repository.PsStudentRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class StudentService extends ABaseService<PsStudentTable, PsStudentRepository>
    implements PsStudentRepository, IService<PsStudentTable> {

  @Override
  public PsStudentTable build(PsStudentTable entity) {
    return entity;
  }

  @Override
  public List<PsStudentTable> searchIlikeNameOrCode(String value) {
    return super.mainRepository.searchIlikeNameOrCode(value);
  }

}
