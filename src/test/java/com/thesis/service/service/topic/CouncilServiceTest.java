package com.thesis.service.service.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import java.util.List;
import java.util.function.Consumer;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.repository.topic.CouncilRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.user.NotificationService;

public class CouncilServiceTest
    extends AEntityServiceTest<CouncilTable, CouncilRepository, CouncilService> {

  @Override
  protected CouncilService spyService() {
    return spy(new CouncilService(
        mock(CouncilMemberRepository.class),
        mock(TopicRepository.class),
        mock(NotificationService.class)));
  }

  @Override
  protected Consumer<CouncilTable> extendEntity() {
    return (CouncilTable entity) -> entity.setMembers(List.of());
  }

}
