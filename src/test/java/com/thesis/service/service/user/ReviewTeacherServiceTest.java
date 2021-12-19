package com.thesis.service.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import java.util.List;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseServiceTest;
import com.thesis.service.service.MessageSourceService;
import com.thesis.service.service.system.testsuite.SemesterServiceTS;
import com.thesis.service.service.system.testsuite.SubjectDepartmentServiceTS;
import com.thesis.service.service.topic.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.test.util.ReflectionTestUtils;

public class ReviewTeacherServiceTest extends ABaseServiceTest {

  @InjectMocks
  ReviewTeacherService service;
  @Mock
  UserRepository userRepository;
  @Mock
  TopicRepository topicRepository;
  @Mock
  MessageSourceService messageSourceService;
  @Mock
  NotificationService notificationService;
  @Mock
  TopicService topicService;

  @BeforeEach
  void beforeEach() {
    when(topicService.map(anyCollection())).thenAnswer(i -> i.getArgument(0));
    ReflectionTestUtils.setField(service, "userRepository", userRepository);
    ReflectionTestUtils.setField(service, "topicRepository", topicRepository);
    ReflectionTestUtils.setField(service, "topicService", topicService);
  }

  @Test
  void assignReview_empty() {
    var topic = new TopicTable()
        .setReviewTeachers(List.of());
    when(topicService.update(topic)).thenReturn(topic);
    assertNotNull(service.assignReview(topic));
  }

  @Test
  void assignReview_1teacher() {
    var topic = new TopicTable()
        .setReviewTeachers(List.of(new UserTable()));
    when(topicService.update(topic)).thenReturn(topic);
    assertNotNull(service.assignReview(topic));
  }

  @Test
  void getTopicReview_notFound() {
    when(topicRepository.findAll(Mockito.<Example<TopicTable>>any())).thenReturn(List.of());
    var actual = service.getTopicReview(
        SubjectDepartmentServiceTS.VALID_ID,
        SemesterServiceTS.CURRENT_SEMESTER_NAME);
    assertEquals(List.of(), actual);
  }

  @Test
  void getTopicReview_notContainStudent() {
    var queryResult = List.of(new TopicTable());
    when(topicRepository.findAll(Mockito.<Example<TopicTable>>any())).thenReturn(queryResult);
    var actual = service.getTopicReview(
        SubjectDepartmentServiceTS.VALID_ID,
        SemesterServiceTS.CURRENT_SEMESTER_NAME);
    assertEquals(List.of(), actual);
  }

  @Test
  void getTopicReview_studentNotHaveMidMark() {
    var queryResult = List.of(new TopicTable().setStudents(List.of(new TopicStudentTable())));
    when(topicRepository.findAll(Mockito.<Example<TopicTable>>any())).thenReturn(queryResult);
    var actual = service.getTopicReview(
        SubjectDepartmentServiceTS.VALID_ID,
        SemesterServiceTS.CURRENT_SEMESTER_NAME);
    assertEquals(List.of(), actual);
  }

  @Test
  void getTopicReview_studentMidMarkFail() {
    var queryResult = List.of(new TopicTable().setStudents(
        List.of(new TopicStudentTable().setMidPass(false))));
    when(topicRepository.findAll(Mockito.<Example<TopicTable>>any())).thenReturn(queryResult);
    var actual = service.getTopicReview(
        SubjectDepartmentServiceTS.VALID_ID,
        SemesterServiceTS.CURRENT_SEMESTER_NAME);
    assertEquals(List.of(), actual);
  }

  @Test
  void getTopicReview_studentMidMarkPass() {
    var queryResult = List.of(new TopicTable().setStudents(
        List.of(new TopicStudentTable().setMidPass(true))));
    when(topicRepository.findAll(Mockito.<Example<TopicTable>>any())).thenReturn(queryResult);
    var actual = service.getTopicReview(
        SubjectDepartmentServiceTS.VALID_ID,
        SemesterServiceTS.CURRENT_SEMESTER_NAME);
    assertEquals(queryResult, actual);
  }

}
