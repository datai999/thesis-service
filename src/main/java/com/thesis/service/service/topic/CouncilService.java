package com.thesis.service.service.topic;

import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.repository.topic.CouncilRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.br.ConstDataService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilService extends ABaseService<CouncilTable, CouncilRepository> {

  final ConstDataService constDataService;

  @Override
  public void preBuild(CouncilTable entity) {
    // entity.setById(constDataService, "role");
  }

}
