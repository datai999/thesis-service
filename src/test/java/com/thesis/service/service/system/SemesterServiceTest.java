package com.thesis.service.service.system;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.TimerNotificationService;

public class SemesterServiceTest
    extends AEntityServiceTest<SemesterTable, SemesterRepository, SemesterService> {

  @Override
  protected SemesterService spyService() {
    return spy(new SemesterService(
        mock(TimerNotificationService.class)));
  }

}
