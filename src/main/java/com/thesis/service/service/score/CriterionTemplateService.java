package com.thesis.service.service.score;

import com.thesis.service.model.score.CriterionTemplateTable;
import com.thesis.service.repository.score.CriterionTemplateRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriterionTemplateService
    extends ABaseService<CriterionTemplateTable, CriterionTemplateRepository> {

  final CriterionService criterionService;

  @Override
  protected void preBuild(CriterionTemplateTable entity) {
    // entity.setById(criterionService, "criterion");
  }

}
