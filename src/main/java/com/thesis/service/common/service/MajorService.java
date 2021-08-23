package com.thesis.service.common.service;

import com.thesis.service.common.model.SyMajorTable;
import com.thesis.service.common.repository.SyMajorRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MajorService extends ABaseService<SyMajorTable, SyMajorRepository> {

  @Override
  protected void preBuild(SyMajorTable entity) {
    // TODO Auto-generated method stub
  }

}
