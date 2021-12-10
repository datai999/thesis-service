package com.thesis.service.service.score;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.repository.score.TemplateRepository;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.service.AEntityServiceTest;

public class TemplateServiceTest
    extends AEntityServiceTest<TemplateTable, TemplateRepository, TemplateService> {

  @Override
  protected TemplateService spyService() {
    return spy(new TemplateService(
        mock(CouncilMemberRepository.class),
        mock(CriterionService.class)));
  }

}
