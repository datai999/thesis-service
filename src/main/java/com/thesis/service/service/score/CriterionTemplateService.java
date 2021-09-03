package com.thesis.service.service.score;

import com.thesis.service.model.model.ScCriterionTemplateTable;
import com.thesis.service.repository.score.ScCriterionTemplateRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriterionTemplateService
    extends ABaseService<ScCriterionTemplateTable, ScCriterionTemplateRepository> {

  final CriterionService criterionService;

  @Override
  protected void preBuild(ScCriterionTemplateTable entity) {
    entity.setById(criterionService, "criterion");
  }

}
