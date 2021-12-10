package com.thesis.service.service.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.List;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseServiceTest;
import com.thesis.service.service.MessageSourceService;
import com.thesis.service.service.topic.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

public class ReviewTeacherServiceTest extends ABaseServiceTest {

  @InjectMocks
  ReviewTeacherService service;
  @Mock
  UserRepository userRepository;
  @Mock
  TopicService topicService;
  @Mock
  MessageSourceService messageSourceService;
  @Mock
  NotificationService notificationService;

  @BeforeEach
  void beforeEach() {
    ReflectionTestUtils.setField(service, "userRepository", userRepository);
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

}
