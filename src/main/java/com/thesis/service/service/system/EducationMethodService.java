package com.thesis.service.service.system;

import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.repository.system.EducationMethodRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EducationMethodService
    extends ABaseService<EducationMethodTable, EducationMethodRepository> {

  public Object findAll(Sort sort) {
    return mapper.map(super.repository.findAll(sort), BaseResponse.class);
  }

}
