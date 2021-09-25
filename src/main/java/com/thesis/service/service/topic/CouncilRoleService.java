package com.thesis.service.service.topic;

import com.thesis.service.dto.topic.resposne.CouncilRoleResponse;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.repository.topic.CouncilRoleRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class CouncilRoleService extends ABaseService<CouncilRoleTable, CouncilRoleRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return CouncilRoleResponse.class;
  }

  @Override
  public Object create(CouncilRoleTable entity) {
    entity.setMin(ObjectUtils.defaultIfNull(entity.getMin(), 1));
    entity.setMax(ObjectUtils.defaultIfNull(entity.getMax(), 1));
    entity.setDisplayOrder(ObjectUtils.defaultIfNull(entity.getDisplayOrder(), 1));
    return this.repository.save(entity).getId();
  }

  public Object findActive() {
    var response = super.repository.findByDeletedFalseOrderByDisplayOrder();
    return super.mapper.map(response, CouncilRoleResponse.class);
  }

}
