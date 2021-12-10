package com.thesis.service.service.score;

import static org.mockito.Mockito.spy;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.repository.score.CriterionRepository;
import com.thesis.service.service.AEntityServiceTest;

public class CriterionServiceTest
    extends AEntityServiceTest<CriterionTable, CriterionRepository, CriterionService> {

  @Override
  protected CriterionService spyService() {
    return spy(new CriterionService());
  }

}
