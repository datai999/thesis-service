package com.thesis.service.service.score;

import com.thesis.service.dto.score.response.ScoreResponse;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;

@Service
public class ScoreService extends ABaseService<ScoreTable, ScoreRepository> {

  protected Class<?> getResponseClass() {
    return ScoreResponse.class;
  }

}
