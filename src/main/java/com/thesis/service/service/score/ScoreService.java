package com.thesis.service.service.score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.dto.score.response.ScoreResponse;
import com.thesis.service.dto.score.response.TopicScoreResponse;
import com.thesis.service.dto.score.response.UserScoreResponse;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.user.NotificationService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService extends ABaseService<ScoreTable, ScoreRepository> {

  private final UserRepository userRepository;
  private final TopicRepository topicRepository;
  private final NotificationService notificationService;

  @Override
  protected Class<?> getResponseClass() {
    return ScoreResponse.class;
  }

  @Override
  @Transactional
  public Object saveAll(Collection<ScoreTable> entities) {
    var response = this.repository.saveAll(entities);
    var student = response.get(0).getStudent();
    var actorTag = super.messageSource.toUserTag(super.getAuth());
    var message = super.messageSource.getMessage(MessageCode.Mark.FINAL, actorTag);
    notificationService.notify(List.of(student), message);
    return this.map(response);
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

  public Object getTopicScore(long topicId) {
    var topic = topicRepository.findById(topicId).orElseThrow();
    var result = new ArrayList<TopicScoreResponse>();

    var guideScore = super.repository.findAll(Example.of(
        new ScoreTable().setTopic(topic).setGuideTeacher(true)));
    var guideScoreResponse =
        new TopicScoreResponse(topic, topic.getTopicGuideTeachers(), guideScore)
            .setName(super.messageSource.getMessage(MessageCode.GUIDE_TEACHER_MINI));
    result.add(guideScoreResponse);

    var reviewScore = super.repository.findAll(Example.of(
        new ScoreTable().setTopic(topic).setReviewTeacher(true)));
    var reviewScoreResponse = new TopicScoreResponse(topic, topic.getReviewTeachers(), reviewScore)
        .setName(super.messageSource.getMessage(MessageCode.REVIEW_TEACHER_MINI));
    result.add(reviewScoreResponse);

    if (Objects.isNull(topic.getCouncil()))
      return result;

    var councilScore = super.repository.findAll(Example.of(
        new ScoreTable().setTopic(topic).setGuideTeacher(false).setReviewTeacher(false)));
    var councilRoleScore = councilScore.stream().collect(
        Collectors.groupingBy(ScoreTable::getCouncilRole));

    topic.getCouncil().getMembers().stream()
        .collect(Collectors.groupingBy(CouncilMemberTable::getRole))
        .entrySet().stream()
        .sorted((a, b) -> a.getKey().getDisplayOrder().compareTo(b.getKey().getDisplayOrder()))
        .forEachOrdered(e -> result.add(
            new TopicScoreResponse(
                topic,
                e.getValue().stream()
                    .map(CouncilMemberTable::getMember).collect(Collectors.toList()),
                councilRoleScore.get(e.getKey()))
                    .setId(e.getKey().getId())
                    .setName(e.getKey().getName())));

    return result;
  }

}
