package com.thesis.service.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.thesis.service.model.topic.TopicGuideTeacherTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseServiceTest;
import com.thesis.service.service.system.testsuite.SemesterServiceTS;
import com.thesis.service.service.topic.TopicService;
import com.thesis.service.service.user.testsuite.GuideTeacherServiceTS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * test GuideTeacherService
 */
public class GuideTeacherServiceTest extends ABaseServiceTest {

  @InjectMocks
  GuideTeacherService service;
  @Mock
  UserRepository userRepository;
  @Mock
  TopicService topicService;

  @BeforeEach
  void beforeEach() {
    this.userRepository = mock(UserRepository.class);
    ReflectionTestUtils.setField(this.service, "userRepository", this.userRepository);
    ReflectionTestUtils.setField(this.service, "topicService", this.topicService);
  }

  @Test
  @DisplayName("Throw error when not found user")
  void getTopic_userNotFound() {
    when(userRepository.findById(GuideTeacherServiceTS.INVALID_USER_ID))
        .thenThrow(new NoSuchElementException());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.getTopic(GuideTeacherServiceTS.INVALID_USER_ID,
            SemesterServiceTS.CURRENT_SEMESTER_NAME));
    assertEquals(null, exception.getMessage());
  }

  @Test
  @DisplayName("Teacher not guide topic")
  void getTopic_teacherNotGuideTopic() {
    when(userRepository.findById(GuideTeacherServiceTS.VALID_USER_ID))
        .thenReturn(Optional.of(new UserTable().setTopicGuides(List.of())));
    var result = service.getTopic(GuideTeacherServiceTS.VALID_USER_ID,
        SemesterServiceTS.CURRENT_SEMESTER_NAME);
    assertEquals(List.of(), result);
  }

  @Test
  @DisplayName("Teacher not guide topic in current semester")
  void teacherNotGuideTopicInCurrentSemester() {
    when(userRepository.findById(GuideTeacherServiceTS.VALID_USER_ID))
        .thenReturn(Optional.of(new UserTable()
            .setTopicGuides(
                List.of(new TopicGuideTeacherTable()
                    .setTopic(new TopicTable()
                        .setSemester(SemesterServiceTS.PREVIOUS_SEMESTER.get()))))));
    service.getTopic(GuideTeacherServiceTS.VALID_USER_ID,
        SemesterServiceTS.CURRENT_SEMESTER_NAME);
  }

  @Test
  @DisplayName("Teacher have guide topic in current semester")
  void teacherHaveGuideTopicInCurrentSemester() {
    when(userRepository.findById(GuideTeacherServiceTS.VALID_USER_ID))
        .thenReturn(Optional.of(new UserTable()
            .setTopicGuides(
                List.of(new TopicGuideTeacherTable()
                    .setTopic(new TopicTable()
                        .setSemester(SemesterServiceTS.CURRENT_SEMESTER.get()))))));
    service.getTopic(GuideTeacherServiceTS.VALID_USER_ID,
        SemesterServiceTS.CURRENT_SEMESTER_NAME);
  }

}
