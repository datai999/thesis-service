package com.thesis.service.service.system;

import com.thesis.service.model.system.SyMajorTable;
import com.thesis.service.repository.system.SyMajorRepository;
import com.thesis.service.service.ABaseService;
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
