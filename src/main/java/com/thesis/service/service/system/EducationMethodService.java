package com.thesis.service.service.system;

import java.util.stream.Collectors;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.repository.system.EducationMethodRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EducationMethodService
    extends AbstractBaseService<EducationMethodTable, EducationMethodRepository> {

  public Object findAll(Sort sort) {
    return super.repository.findAll(sort).parallelStream()
        .map(new BaseResponse()::map).collect(Collectors.toList());
  }

}
