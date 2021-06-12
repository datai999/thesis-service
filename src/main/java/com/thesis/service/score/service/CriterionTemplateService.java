package com.thesis.service.score.service;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.score.model.ScCriterionTemplateTable;
import com.thesis.service.score.repository.ScCriterionTemplateRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriterionTemplateService extends ABaseService<ScCriterionTemplateTable, ScCriterionTemplateRepository> {

  final CriterionService criterionService;

  @Override
  protected void preBuild(ScCriterionTemplateTable entity) {
    entity.setById(criterionService, "criterion");
  }

}
