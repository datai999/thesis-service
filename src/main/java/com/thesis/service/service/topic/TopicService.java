package com.thesis.service.service.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.thesis.service.advice.BusinessException;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.constant.TopicState;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.repository.topic.TopicGuideTeacherRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.topic.TopicStudentRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.system.SemesterService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends ABaseService<TopicTable, TopicRepository> {

  private final SemesterRepository semesterRepository;
  private final TopicGuideTeacherRepository topicGuideTeacherRepository;
  private final TopicStudentRepository topicStudentRepository;
  private final ScoreRepository scoreRepository;

  private final SemesterService semesterService;

  @Override
  public Function<TopicTable, Object> mapping() {
    return TopicResponse::new;
  }

  @Override
  @Transactional
  public Object create(TopicTable entity) {
    var semester = semesterRepository.findCurrentSemester();

    if (!semesterService.inCreateTime(entity.getThesis())) {
      throw BusinessException.code(entity.getThesis()
          ? MessageCode.Semester.END_CREATE_THESIS
          : MessageCode.Semester.END_CREATE_OUTLINE);
    }

    entity.setSemester(semester).setSubjectDepartment(this.getAuth().getSubjectDepartment());
    var topicResponse = this.repository.save(entity);

    if (CollectionUtils.isNotEmpty(entity.getGuideTeachers())) {
      var guideTeachers = super.mapper.map(entity.getGuideTeachers(),
          e -> e.setTopic(topicResponse).setMain(false));
      guideTeachers.get(0).setMain(true);
      var guideTeacherResponse = topicGuideTeacherRepository.saveAll(guideTeachers);
      topicResponse.setGuideTeachers(guideTeacherResponse);
    }

    if (CollectionUtils.isNotEmpty(entity.getStudents())) {
      var defaultMidPass = semesterService.getCurrentSemester()
          .getProperty(topicResponse.getThesis()).getDefaultMid();
      var students = super.mapper.map(entity.getStudents(),
          e -> e.setTopic(topicResponse).setMidPass(defaultMidPass));
      var studentResponse = topicStudentRepository.saveAll(students);
      topicResponse.setStudents(studentResponse);
    }

    return this.map(topicResponse);
  }

  @Override
  @Transactional
  public Object update(TopicTable entity) {
    var existEntity = this.repository.findById(entity.getId()).orElseThrow();
    entity.setCreatedAt(existEntity.getCreatedAt());

    var topicResponse = this.repository.save(entity);

    topicGuideTeacherRepository.deleteByTopic(entity.getId());
    if (CollectionUtils.isNotEmpty(entity.getGuideTeachers())) {
      var guideTeachers = super.mapper.map(entity.getGuideTeachers(),
          e -> e.setTopic(topicResponse).setMain(false));
      guideTeachers.get(0).setMain(true);
      var guideTeacherResponse = topicGuideTeacherRepository.saveAll(guideTeachers);
      topicResponse.setGuideTeachers(guideTeacherResponse);
    }

    var updateStudentIds = super.mapper.map(entity.getTopicStudents(), UserTable::getId);
    var existStudentIds = super.mapper.map(topicResponse.getTopicStudents(), UserTable::getId);

    var newStudents = ObjectUtils
        .defaultIfNull(entity.getStudents(), new ArrayList<TopicStudentTable>())
        .stream().filter(e -> !existStudentIds.contains(e.getStudent().getId()))
        .collect(Collectors.toList());
    if (CollectionUtils.isNotEmpty(newStudents)) {
      var defaultMidPass = semesterService.getCurrentSemester()
          .getProperty(topicResponse.getThesis()).getDefaultMid();
      var students = super.mapper.map(newStudents,
          e -> e.setTopic(topicResponse).setMidPass(defaultMidPass));
      topicStudentRepository.saveAll(students);
    }

    var removeStudents = ObjectUtils
        .defaultIfNull(topicResponse.getStudents(), new ArrayList<TopicStudentTable>())
        .stream().filter(e -> !updateStudentIds.contains(e.getStudent().getId()))
        .collect(Collectors.toList());
    if (CollectionUtils.isNotEmpty(removeStudents)) {
      topicStudentRepository.deleteAll(removeStudents);
    }

    return this.map(topicResponse);
  }

  @Override
  public Object findByExample(TopicTable entity, Sort sort) {
    entity.setCreatedAt(null).setUpdatedAt(null);
    var example = Example.of(entity);
    var response = this.repository.findAll(example, sort);

    BiPredicate<TopicTable, Function<TopicTable, List<? extends BaseTable>>> filter =
        (targetTopic, map) -> {
          var mapToIds =
              map.andThen(e -> e.stream().map(BaseTable::getId).collect(Collectors.toList()));
          return mapToIds.apply(targetTopic).containsAll(mapToIds.apply(entity));
        };

    if (CollectionUtils.isNotEmpty(entity.getEducationMethods())) {
      response = response.stream()
          .filter(e -> filter.test(e, TopicTable::getEducationMethods))
          .collect(Collectors.toList());
    }
    if (CollectionUtils.isNotEmpty(entity.getMajors()))
      response = response.stream()
          .filter(e -> filter.test(e, TopicTable::getMajors))
          .collect(Collectors.toList());

    return this.map(response);
  }

  public Object findNeedAssignCouncil(long subjectDepartmentId, Sort sort) {
    var response = super.repository.findNeedAssignCouncil(subjectDepartmentId, sort);
    return super.map(response);
  }

  public TopicState getState(TopicTable entity) {
    if (CollectionUtils.isEmpty(entity.getStudents()))
      return TopicState.REGISTER;
    if (entity.getStudents().stream().anyMatch(e -> Objects.isNull(e.getMidPass())))
      return TopicState.MID;

    var studentMidPass = entity.getStudents().stream()
        .filter(TopicStudentTable::getMidPass)
        .map(TopicStudentTable::getStudent).collect(Collectors.toList());
    if (CollectionUtils.isEmpty(studentMidPass))
      return TopicState.COMPLETE;

    var scoreExample = new ScoreTable().setTopic(entity);
    var scores = scoreRepository.findAll(Example.of(scoreExample));
    var completeFlag = new AtomicBoolean(true);
    studentMidPass.forEach(student -> {
      var notHaveScore = scores.stream()
          .noneMatch(score -> student.getId().equals(score.getStudent().getId()));
      if (notHaveScore)
        completeFlag.set(false);
    });

    return completeFlag.get() ? TopicState.COMPLETE : TopicState.FINAL;
  }

}
