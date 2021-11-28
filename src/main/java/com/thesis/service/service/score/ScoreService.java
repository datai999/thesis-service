package com.thesis.service.service.score;

import java.util.Set;
import java.util.stream.Collectors;
import com.thesis.service.dto.score.response.ScoreResponse;
import com.thesis.service.dto.score.response.UserScoreResponse;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService extends ABaseService<ScoreTable, ScoreRepository> {

  private final UserRepository userRepository;

  @Override
  protected Class<?> getResponseClass() {
    return ScoreResponse.class;
  }

  public Object getStudentScore(ScoreTable entity) {
    var queryResult = this.repository.findAll(Example.of(entity));
    var studentScore = queryResult.stream().collect(Collectors.groupingBy(ScoreTable::getStudent));
    return studentScore.entrySet().parallelStream().map(UserScoreResponse::new);
  }

  public Object getTeacherScore(ScoreTable entity, Set<Long> teacherIds) {
    var queryResult = this.repository.findAll(Example.of(entity));
    queryResult = queryResult.stream()
        .filter(e -> teacherIds.contains(e.getTeacher().getId()))
        .collect(Collectors.toList());

    if (CollectionUtils.isNotEmpty(entity.getTemplate().getCouncilRoles())) {
      var roleId = entity.getTemplate().getCouncilRoles().get(0).getId();
      queryResult = queryResult.stream()
          .filter(e -> e.getTemplate().getCouncilRoles().stream()
              .anyMatch(role -> role.getId().equals(roleId)))
          .collect(Collectors.toList());
    }

    var studentScore = queryResult.stream().collect(
        Collectors.groupingBy(score -> score.getTeacher().getId()));
    var teachers = userRepository.findAllById(teacherIds);
    return teachers.stream().map(e -> new UserScoreResponse(e, studentScore.get(e.getId())));
  }

}
