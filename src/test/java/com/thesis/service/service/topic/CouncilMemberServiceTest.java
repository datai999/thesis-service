package com.thesis.service.service.topic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.repository.topic.CouncilRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.topic.testsuite.CouncilMemberServiceTS;
import com.thesis.service.service.topic.testsuite.CouncilServiceTS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class CouncilMemberServiceTest
    extends AEntityServiceTest<CouncilMemberTable, CouncilMemberRepository, CouncilMemberService> {

  private CouncilRepository councilRepository;

  @Override
  protected CouncilMemberService spyService() {
    return spy(new CouncilMemberService(
        mock(CouncilRepository.class),
        mock(CouncilService.class)));
  }

  @BeforeEach
  void beforeEachCouncilMemberServiceTest() {
    this.councilRepository = mock(CouncilRepository.class);
    ReflectionTestUtils.setField(service, "councilRepository", councilRepository);
  }

  @Test
  void checkConflictTimeLine_councilNotFound() {
    when(councilRepository.findById(CouncilServiceTS.VALID_ID.get()))
        .thenThrow(new NoSuchElementException());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.checkConflictTimeLine(
            CouncilServiceTS.VALID_ID.get(),
            CouncilMemberServiceTS.RESERVED_DATE.get()));
    assertEquals(null, exception.getMessage());
  }

  @Test
  void checkConflictTimeLine_success() {
    when(councilRepository.findById(CouncilServiceTS.VALID_ID.get()))
        .thenReturn(Optional.of(new CouncilTable().setMembers(
            List.of(new CouncilMemberTable().setMember(new UserTable())))));
    var actual = service.checkConflictTimeLine(
        CouncilServiceTS.VALID_ID.get(),
        CouncilMemberServiceTS.RESERVED_DATE.get());
    assertEquals(List.of(), actual);
  }

}
