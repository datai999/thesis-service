package com.thesis.service.service.score;

import com.thesis.service.model.model.ScCriterionTable;
import com.thesis.service.repository.score.ScCriterionRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriterionService extends ABaseService<ScCriterionTable, ScCriterionRepository> {

  @Override
  protected void preBuild(ScCriterionTable entity) {
    // do nothing
  }

}
