package com.thesis.service.score.service;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.score.model.ScCriterionTable;
import com.thesis.service.score.repository.ScCriterionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriterionService extends ABaseService<ScCriterionTable, ScCriterionRepository> {

  @Override
  protected void preBuild(ScCriterionTable entity) {
    // TODO Auto-generated method stub

  }

}
