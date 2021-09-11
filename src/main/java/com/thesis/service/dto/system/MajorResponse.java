package com.thesis.service.dto.system;

import com.thesis.service.model.system.MajorTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;

@Data
public class MajorResponse {

  private Long id;
  private String name;

  public MajorResponse map(MajorTable entity) {
    return ContextAccessor.getModelMapper()
        .map(entity, MajorResponse.class);
  }

}
