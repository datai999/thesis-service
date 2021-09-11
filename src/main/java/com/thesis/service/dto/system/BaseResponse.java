package com.thesis.service.dto.system;

import com.thesis.service.model.BaseTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;

@Data
public class BaseResponse {

  private Long id;
  private String name;

  public <T extends BaseTable> BaseResponse map(T entity) {
    return ContextAccessor.getModelMapper()
        .map(entity, BaseResponse.class);
  }

}
