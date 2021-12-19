package com.thesis.service.service.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.TimerNotificationService;
import com.thesis.service.service.system.testsuite.SemesterServiceTS;
import com.thesis.service.service.topic.testsuite.CouncilServiceTS;
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
    when(repository.findCurrentSemester())
        .thenReturn(SemesterServiceTS.CURRENT_SEMESTER.get());
    var actual = service.getCurrentSemester();
    assertEquals(SemesterServiceTS.CURRENT_SEMESTER.get(), actual);
  }

  @Test
  void allowStudentRegisterCancelTopic_true() {
    when(repository.findCurrentSemester())
        .thenReturn(SemesterServiceTS.CURRENT_SEMESTER.get()
            .setRegisterTopicStart(LocalDateTime.now())
            .setRegisterTopicEnd(LocalDateTime.now()));
    assertTrue(service.allowStudentRegisterCancelTopic());
  }

  @Test
  void allowStudentRegisterCancelTopic_before() {
    when(repository.findCurrentSemester())
        .thenReturn(SemesterServiceTS.CURRENT_SEMESTER.get()
            .setRegisterTopicStart(LocalDateTime.now().plusMinutes(3))
            .setRegisterTopicEnd(LocalDateTime.now()));
    assertFalse(service.allowStudentRegisterCancelTopic());
  }

  @Test
  void allowStudentRegisterCancelTopic_after() {
    when(repository.findCurrentSemester())
        .thenReturn(SemesterServiceTS.CURRENT_SEMESTER.get()
            .setRegisterTopicStart(LocalDateTime.now())
            .setRegisterTopicEnd(LocalDateTime.now().minusMinutes(3)));
    assertFalse(service.allowStudentRegisterCancelTopic());
  }

  @Test
  @Disabled
  void setCurrentSemester_notFound() {
    when(repository.findById(CouncilServiceTS.VALID_ID.get()))
        .thenThrow(new NoSuchElementException());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.setCurrentSemester(SemesterServiceTS.CURRENT_SEMESTER.get().getId()));
    assertEquals("No value present", exception.getMessage());
  }

}
