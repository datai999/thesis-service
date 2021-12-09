package com.thesis.service.service.score;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.user.NotificationService;
import org.junit.jupiter.api.BeforeAll;

public class ScoreServiceTest
    extends AEntityServiceTest<ScoreTable, ScoreRepository, ScoreService> {

  @BeforeAll
  private void beforeAllTopicService() {
    super.entity.setStudent(new UserTable());
    super.service = spy(new ScoreService(
        mock(UserRepository.class),
        mock(TopicRepository.class),
        mock(NotificationService.class)));
    super.initService();
  }

}
