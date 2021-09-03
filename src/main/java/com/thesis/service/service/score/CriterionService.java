package com.thesis.service.service.score;

import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.repository.score.CriterionRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriterionService extends ABaseService<CriterionTable, CriterionRepository> {

  @Override
  protected void preBuild(CriterionTable entity) {
    // do nothing
  }

}
