package com.thesis.service.service.topic;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends ABaseService<TopicTable, TopicRepository> {

  private final SemesterRepository semesterRepository;

  @Override
  protected Function<TopicTable, ?> mapping() {
    return TopicResponse::new;
  }

  @Override
  public Object create(TopicTable entity) {
    var semester = semesterRepository.findCurrentSemester();
    entity.setSemester(semester).setSubjectDepartment(this.getAuth().getSubjectDepartment());
    return this.repository.save(entity).getId();
  }

  public Function<TopicTable, List<BaseTable>> getGenericPredicate(
      Function<TopicTable, List<? extends BaseTable>> func) {
    // some additional code goes here
    return func
        .andThen(e -> e.parallelStream().map(BaseTable.class::cast).collect(Collectors.toList()));
  }

  @Override
  public Object findByExample(TopicTable entity, Sort sort) {
    entity.setCreatedAt(null).setUpdatedAt(null);
    var example = Example.of(entity);
    var response = this.repository.findAll(example, sort);

    BiPredicate<TopicTable, Function<TopicTable, List<? extends BaseTable>>> filter =
        (targetTopic, map) -> {
          if (CollectionUtils.isEmpty(map.apply(targetTopic)))
            return false;
          var mapToIds =
              map.andThen(e -> e.stream().map(BaseTable::getId).collect(Collectors.toList()));
          return mapToIds.apply(targetTopic).containsAll(mapToIds.apply(entity));
        };

    if (CollectionUtils.isNotEmpty(entity.getEducationMethods()))
      response = response.stream()
          .filter(e -> filter.test(e, TopicTable::getEducationMethods))
          .collect(Collectors.toList());
    if (CollectionUtils.isNotEmpty(entity.getMajors()))
      response = response.stream()
          .filter(e -> filter.test(e, TopicTable::getMajors))
          .collect(Collectors.toList());

    return this.map(response);
  }

  @Override
  public Object update(TopicTable entity) {
    var existEntity = this.repository.findById(entity.getId()).orElseThrow();
    entity.setStudents(existEntity.getStudents())
        .setCreatedAt(existEntity.getCreatedAt());
    var response = this.repository.save(entity);
    return this.map(response);
  }

  public Object findNeedAssignCouncil(long subjectDepartmentId, Sort sort) {
    var response = super.repository.findNeedAssignCouncil(subjectDepartmentId, sort);
    return super.map(response);
  }

}
