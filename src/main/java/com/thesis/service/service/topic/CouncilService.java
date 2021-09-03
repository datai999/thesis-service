package com.thesis.service.service.topic;

import com.thesis.service.model.topic.TpCouncilTable;
import com.thesis.service.repository.topic.TpCouncilRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.br.ConstDataService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilService extends ABaseService<TpCouncilTable, TpCouncilRepository> {

  final ConstDataService constDataService;

  @Override
  public void preBuild(TpCouncilTable entity) {
    entity.setById(constDataService, "role");
  }

}
