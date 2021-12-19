package com.thesis.service.service.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import java.util.function.Consumer;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicStudentRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.user.NotificationService;

public class TopicStudentServiceTest
    extends AEntityServiceTest<TopicStudentTable, TopicStudentRepository, TopicStudentService> {

  @Override
  protected TopicStudentService spyService() {
    return spy(new TopicStudentService(
        mock(NotificationService.class)));
  }

  @Override
  protected Consumer<TopicStudentTable> extendEntity() {
    return (TopicStudentTable entity) -> entity
        .setTopic(new TopicTable())
        .setStudent(new UserTable());
  }

}
