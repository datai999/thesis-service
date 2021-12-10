package com.thesis.service.service.system;

import static org.mockito.Mockito.spy;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.repository.system.EducationMethodRepository;
import com.thesis.service.service.AEntityServiceTest;

public class EducationMethodServiceTest
    extends
    AEntityServiceTest<EducationMethodTable, EducationMethodRepository, EducationMethodService> {

  @Override
  protected EducationMethodService spyService() {
    return spy(new EducationMethodService());
  }

}
