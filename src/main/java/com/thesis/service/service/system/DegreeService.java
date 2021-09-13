package com.thesis.service.service.system;

import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.repository.system.DegreeRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DegreeService extends ABaseService<DegreeTable, DegreeRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return BaseResponse.class;
  }

}
