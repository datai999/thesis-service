package com.thesis.service.service.score;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import java.util.function.Consumer;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.user.NotificationService;

public class ScoreServiceTest
    extends AEntityServiceTest<ScoreTable, ScoreRepository, ScoreService> {

  @Override
  protected ScoreService spyService() {
    return spy(new ScoreService(
        mock(UserRepository.class),
        mock(TopicRepository.class),
        mock(NotificationService.class)));
  }

  @Override
  protected Consumer<ScoreTable> extendEntity() {
    return (ScoreTable entity) -> entity.setStudent(new UserTable());
  }

}
