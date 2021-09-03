package com.thesis.service.topic.dto.response;

import java.util.Objects;
import java.util.stream.Stream;
import com.thesis.service.common.model.SyEducationMethodTable;
import com.thesis.service.common.utils.ContextAccessor;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.topic.model.TpTopicTable;
import org.apache.commons.collections4.CollectionUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TopicFlatResponse extends TpTopicTable {

  private Stream<String> names;
  private String type;
  private Stream<String> majorNames;
  private Stream<String> educationMethodNames;
  private Stream<String> guideTeacherNames;

  public static TopicFlatResponse from(TpTopicTable entity) {

    var result = ContextAccessor.getModelMapper()
        .map(entity, TopicFlatResponse.class)
        .setType(entity.getThesis() ? "Luận văn" : "Đề cương");

    if (Objects.nonNull(entity.getName())) {
      result.setNames(Stream.of(entity.getName().getVi(), entity.getName().getEn()));
    }

    if (CollectionUtils.isNotEmpty(entity.getEducationMethods())) {
      result.setEducationMethodNames(
          entity.getEducationMethods().stream().map(SyEducationMethodTable::getName));
    }

    if (CollectionUtils.isNotEmpty(entity.getMajors())) {
      result.setMajorNames(
          entity.getMajors().stream().map(major -> major.getName().getVi()));
    }

    if (CollectionUtils.isNotEmpty(entity.getGuideTeachers())) {
      result.setGuideTeacherNames(
          entity.getGuideTeachers().stream().map(PsTeacherTable::getFullName));
    }

    return result;
  }
}
