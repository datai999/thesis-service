package com.thesis.service.service.system;

import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.repository.system.MajorRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MajorService extends AbstractBaseService<MajorTable, MajorRepository> {

  public Object findAll(Sort sort) {
    return super.repository.findAll(sort).parallelStream().map(new BaseResponse()::map);
  }

}
