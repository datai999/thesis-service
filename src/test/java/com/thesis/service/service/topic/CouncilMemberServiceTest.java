package com.thesis.service.service.topic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.repository.topic.CouncilRepository;
import com.thesis.service.service.AEntityServiceTest;

public class CouncilMemberServiceTest
    extends AEntityServiceTest<CouncilMemberTable, CouncilMemberRepository, CouncilMemberService> {

  @Override
  protected CouncilMemberService spyService() {
    return spy(new CouncilMemberService(
        mock(CouncilRepository.class),
        mock(CouncilService.class)));
  }

}
