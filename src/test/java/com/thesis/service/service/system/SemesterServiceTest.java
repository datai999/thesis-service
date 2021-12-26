package com.thesis.service.service.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.TimerNotificationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SemesterServiceTest
    extends AEntityServiceTest<SemesterTable, SemesterRepository, SemesterService> {

  @Override
  protected SemesterService spyService() {
    return spy(new SemesterService(
        mock(TimerNotificationService.class)));
  }

  @Test
  void getCurrentSemester() {
    when(repository.findCurrentSemester()).thenReturn(entity);
    var actual = service.getCurrentSemester();
    assertEquals(entity, actual);
  }

  @Test
  @Disabled
  void inRegisterTopicTime_true() {
    // when(repository.findCurrentSemester())
    // .thenReturn(new SemesterTable()
    // .setRegisterTopicStart(LocalDateTime.now())
    // .setRegisterTopicEnd(LocalDateTime.now()));
    assertTrue(service.inRegisterTopicTime(false));
  }

  @Test
  @Disabled
  void inRegisterTopicTime_before() {
    // when(repository.findCurrentSemester())
    // .thenReturn(new SemesterTable()
    // .setRegisterTopicStart(LocalDateTime.now().plusMinutes(3))
    // .setRegisterTopicEnd(LocalDateTime.now()));
    assertFalse(service.inRegisterTopicTime(false));
  }

  @Test
  @Disabled
  void inRegisterTopicTime_after() {
    // when(repository.findCurrentSemester())
    // .thenReturn(new SemesterTable()
    // .setRegisterTopicStart(LocalDateTime.now())
    // .setRegisterTopicEnd(LocalDateTime.now().minusMinutes(3)));
    assertFalse(service.inRegisterTopicTime(false));
  }

}
