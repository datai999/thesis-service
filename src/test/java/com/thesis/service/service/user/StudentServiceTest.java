package com.thesis.service.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.thesis.service.advice.BusinessException;
import com.thesis.service.constant.SemesterStatus;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.topic.TopicStudentRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseServiceTest;
import com.thesis.service.service.MessageSourceService;
import com.thesis.service.service.system.SemesterService;
import com.thesis.service.service.topic.TopicService;
import com.thesis.service.service.user.testsuite.StudentServiceTS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Example;
import org.springframework.test.util.ReflectionTestUtils;

public class StudentServiceTest extends ABaseServiceTest {

  @InjectMocks
  StudentService service;
  @Mock
  UserRepository userRepository;
  @Mock
  TopicRepository topicRepository;
  @Mock
  TopicStudentRepository topicStudentRepository;
  @Mock
  TopicService topicService;
  @Mock
  SemesterService semesterService;
  @Mock
  NotificationService notificationService;
  @Mock
  MessageSourceService messageSourceService;

  @BeforeEach
  void beforeEach() {
    ReflectionTestUtils.setField(this.service, "userRepository", userRepository);
    ReflectionTestUtils.setField(this.service, "topicRepository", topicRepository);
    ReflectionTestUtils.setField(this.service, "topicService", topicService);
    ReflectionTestUtils.setField(this.service, "semesterService", semesterService);
  }

  @ParameterizedTest(name = "Test {index}: Language property file contain message code -> {0}")
  @ArgumentsSource(StudentServiceTS.UseMessageCode.class)
  void hasMessageSource(String messageCode) {
    super.hasMessageSourceEachLocale(messageCode);
  }

  @Test
  @DisplayName("Throw error when not found user")
  void getTopic_userNotFound() {
    when(userRepository.findById(StudentServiceTS.INVALID_STUDENT_ID))
        .thenThrow(new NoSuchElementException());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.getTopic(StudentServiceTS.INVALID_STUDENT_ID));
    assertEquals(null, exception.getMessage());
  }

  @Test
  @DisplayName("Student dont have topic execute")
  void getTopic_studentDontHaveTopicExecute() {
    when(userRepository.findById(StudentServiceTS.VALID_STUDENT_ID))
        .thenReturn(Optional.of(new UserTable().setTopicExecutes(List.of())));
    assertEquals(null, service.getTopic(StudentServiceTS.VALID_STUDENT_ID));
  }

  @Test
  void registerTopic_topicNotFound() {
    when(topicRepository.findById(anyLong()))
        .thenThrow(new NoSuchElementException());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.registerTopic(
            StudentServiceTS.VALID_STUDENT_ID,
            StudentServiceTS.INVALID_TOPIC_ID));
    assertEquals(null, exception.getMessage());
  }

  @Test
  void registerTopic_notAllow() {
    when(topicRepository.findById(anyLong()))
        .thenReturn(Optional.of(new TopicTable().setSemester(new SemesterTable())));
    when(semesterService.allowStudentRegisterCancelTopic()).thenReturn(false);
    Exception exception = assertThrows(BusinessException.class,
        () -> service.registerTopic(
            StudentServiceTS.VALID_STUDENT_ID,
            StudentServiceTS.VALID_TOPIC_ID));
    assertEquals(null, exception.getMessage());
  }

  @Test
  void registerTopic_maxStudent() {
    when(topicRepository.findById(anyLong()))
        .thenReturn(Optional.of(
            new TopicTable()
                .setSemester(new SemesterTable())
                .setStudents(List.of())
                .setMaxStudentTake(0)));
    when(semesterService.allowStudentRegisterCancelTopic()).thenReturn(true);
    Exception exception = assertThrows(BusinessException.class,
        () -> service.registerTopic(
            StudentServiceTS.VALID_STUDENT_ID,
            StudentServiceTS.VALID_TOPIC_ID));
    assertEquals(null, exception.getMessage());
  }

  @Test
  void registerTopic_studentNotFound() {
    when(topicRepository.findById(anyLong()))
        .thenReturn(Optional.of(
            new TopicTable()
                .setSemester(new SemesterTable())
                .setStudents(List.of())
                .setMaxStudentTake(1)));
    when(semesterService.allowStudentRegisterCancelTopic()).thenReturn(true);
    when(userRepository.findById(any())).thenThrow(new NoSuchElementException());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.registerTopic(
            StudentServiceTS.INVALID_STUDENT_ID,
            StudentServiceTS.VALID_TOPIC_ID));
    assertEquals(null, exception.getMessage());
  }

  @Test
  void registerTopic_studentExist() {
    when(topicRepository.findById(anyLong()))
        .thenReturn(Optional.of(
            new TopicTable()
                .setSemester(new SemesterTable())
                .setStudents(List.of(new TopicStudentTable()
                    .setStudent(StudentServiceTS.STUDENT.get())))
                .setMaxStudentTake(3)));
    when(semesterService.allowStudentRegisterCancelTopic()).thenReturn(true);
    when(userRepository.findById(any()))
        .thenReturn(Optional.of(StudentServiceTS.STUDENT.get()));
    Exception exception = assertThrows(BusinessException.class,
        () -> service.registerTopic(
            StudentServiceTS.VALID_STUDENT_ID,
            StudentServiceTS.VALID_TOPIC_ID));
    assertEquals(null, exception.getMessage());
  }

  @Test
  void registerTopic_success() {
    when(topicRepository.findById(anyLong()))
        .thenReturn(Optional.of(
            new TopicTable()
                .setSemester(new SemesterTable())
                .setStudents(List.of())
                .setMaxStudentTake(3)));
    when(semesterService.allowStudentRegisterCancelTopic()).thenReturn(true);
    when(userRepository.findById(any()))
        .thenReturn(Optional.of(StudentServiceTS.STUDENT.get()));
    assertEquals(true, service.registerTopic(
        StudentServiceTS.VALID_STUDENT_ID,
        StudentServiceTS.VALID_TOPIC_ID));
  }

  @Test
  void cancelTopic_noValuePresent() {
    var entity = new TopicStudentTable()
        .setTopic(new TopicTable(StudentServiceTS.VALID_TOPIC_ID))
        .setStudent(new UserTable(StudentServiceTS.VALID_STUDENT_ID));
    when(topicStudentRepository.findAll(Example.of(entity)))
        .thenReturn(List.of());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.cancelTopic(
            StudentServiceTS.VALID_STUDENT_ID,
            StudentServiceTS.VALID_TOPIC_ID));
    assertEquals("No value present", exception.getMessage());
  }

  @Test
  void getCurrentTopic_notFound() {
    when(userRepository.findById(StudentServiceTS.INVALID_STUDENT_ID))
        .thenThrow(new NoSuchElementException());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.getTopic(StudentServiceTS.INVALID_STUDENT_ID));
    assertNull(exception.getMessage());
  }

  @Test
  void getCurrentTopic_notHaveTopic() {
    when(userRepository.findById(StudentServiceTS.VALID_STUDENT_ID))
        .thenReturn(Optional.of(new UserTable().setTopicExecutes(List.of())));
    assertNull(service.getTopic(StudentServiceTS.VALID_STUDENT_ID));
  }

  @Test
  void getCurrentTopic_haveTopic() {
    when(userRepository.findById(StudentServiceTS.VALID_STUDENT_ID))
        .thenReturn(Optional.of(
            new UserTable()
                .setTopicExecutes(List.of(new TopicStudentTable()
                    .setTopic(new TopicTable()
                        .setSemester(new SemesterTable().setStatus(SemesterStatus.USING)))))));
    service.getTopic(StudentServiceTS.VALID_STUDENT_ID);
  }

}
