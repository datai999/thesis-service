package com.thesis.service.service.system;

import static org.mockito.Mockito.spy;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.repository.system.DegreeRepository;
import com.thesis.service.service.AEntityServiceTest;

public class DegreeServiceTest
    extends AEntityServiceTest<DegreeTable, DegreeRepository, DegreeService> {

  @Override
  protected DegreeService spyService() {
    return spy(new DegreeService());
  }

}
