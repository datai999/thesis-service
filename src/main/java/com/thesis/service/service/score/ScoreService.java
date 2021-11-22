package com.thesis.service.service.score;

import java.util.stream.Collectors;
import com.thesis.service.dto.score.response.ScoreResponse;
import com.thesis.service.dto.score.response.StudentScoreResponse;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ScoreService extends ABaseService<ScoreTable, ScoreRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return ScoreResponse.class;
  }

  public Object getStudentScore(ScoreTable entity) {
    var queryResult = this.repository.findAll(Example.of(entity));
    var studentScore = queryResult.stream().collect(Collectors.groupingBy(ScoreTable::getStudent));
    return studentScore.entrySet().parallelStream().map(StudentScoreResponse::new);
  }

}
