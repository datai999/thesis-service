package com.thesis.service.service.topic;

import static org.mockito.Mockito.spy;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.repository.topic.CouncilRoleRepository;
import com.thesis.service.service.AEntityServiceTest;

public class CouncilRoleServiceTest
    extends AEntityServiceTest<CouncilRoleTable, CouncilRoleRepository, CouncilRoleService> {

  @Override
  protected CouncilRoleService spyService() {
    return spy(new CouncilRoleService());
  }

}
