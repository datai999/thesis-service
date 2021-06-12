package com.thesis.service.person.service;

import java.util.List;

import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.person.repository.PsTeacherRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class TeacherService extends APersonBaseService<PsTeacherTable, PsTeacherRepository> implements PsTeacherRepository {

  @Override
  protected void preBuild(PsTeacherTable entity) {
    // do nothing
  }

  @Override
  public List<PsTeacherTable> searchIlikeName(String value) {
    return super.mainRepository.searchIlikeName(value);
  }

  @Override
  public List<PsTeacherTable> findAllByCode(Iterable<String> codes) {
    return super.mainRepository.findAllByCode(codes);
  }

}
