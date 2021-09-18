package com.thesis.service.service.system;

import com.thesis.service.dto.system.SemesterResponse;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;

@Service
public class SemesterService extends ABaseService<SemesterTable, SemesterRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return SemesterResponse.class;
  }

}
