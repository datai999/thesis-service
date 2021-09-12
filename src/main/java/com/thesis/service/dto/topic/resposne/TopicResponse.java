package com.thesis.service.dto.topic.resposne;

import java.util.Objects;
import java.util.stream.Stream;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.utils.ContextAccessor;
import org.apache.commons.collections4.CollectionUtils;
import lombok.Data;

@Data
public class TopicResponse {

  private Long id;
  private Integer semester;
  private Integer minStudentTake = 1;
  private Integer maxStudentTake = 3;
  private String description;
  private String task;
  private String documentReference;

  private Stream<String> names;
  private String type;
  private Stream<String> majorNames;
  private Stream<String> educationMethodNames;
  private Stream<String> studentCodeNames;
  private Stream<String> guideTeacherCodeNames;

  public TopicResponse(TopicTable entity) {

    ContextAccessor.getModelMapper().map(entity, this);

    if (Objects.nonNull(entity.getName())) {
      this.names = Stream.of(entity.getName().getVi(), entity.getName().getEn());
    }

    if (CollectionUtils.isNotEmpty(entity.getEducationMethods())) {
      this.educationMethodNames =
          entity.getEducationMethods().stream().parallel().map(EducationMethodTable::getName);
    }

    if (CollectionUtils.isNotEmpty(entity.getMajors())) {
      this.majorNames =
          entity.getMajors().stream().parallel().map(MajorTable::getName);
    }

  }
}
