package com.thesis.service.service.system;

import static org.mockito.Mockito.spy;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.repository.system.MajorRepository;
import com.thesis.service.service.AEntityServiceTest;

public class MajorServiceTest
    extends AEntityServiceTest<MajorTable, MajorRepository, MajorService> {

  @Override
  protected MajorService spyService() {
    return spy(new MajorService());
  }

}
