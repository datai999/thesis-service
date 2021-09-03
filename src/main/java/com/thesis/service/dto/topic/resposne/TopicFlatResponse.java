package com.thesis.service.dto.topic.resposne;

import java.util.Objects;
import java.util.stream.Stream;
import com.thesis.service.model.person.StudentTable;
import com.thesis.service.model.person.TeacherTable;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.utils.ContextAccessor;
import org.apache.commons.collections4.CollectionUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TopicFlatResponse extends TopicTable {

  private Stream<String> names;
  private String type;
  private Stream<String> majorNames;
  private Stream<String> educationMethodNames;
  private Stream<String> guideTeacherCodeNames;
  private Stream<String> studentCodeNames;

  public static TopicFlatResponse from(TopicTable entity) {

    var result = ContextAccessor.getModelMapper()
        .map(entity, TopicFlatResponse.class)
        .setType(entity.getThesis() ? "Luận văn" : "Đề cương");

    if (Objects.nonNull(entity.getName())) {
      result.setNames(Stream.of(entity.getName().getVi(), entity.getName().getEn()));
    }

    if (CollectionUtils.isNotEmpty(entity.getEducationMethods())) {
      result.setEducationMethodNames(
          entity.getEducationMethods().stream().map(EducationMethodTable::getName));
    }

    if (CollectionUtils.isNotEmpty(entity.getMajors())) {
      result.setMajorNames(
          entity.getMajors().stream().map(major -> major.getName().getVi()));
    }

    if (CollectionUtils.isNotEmpty(entity.getGuideTeachers())) {
      result.setGuideTeacherCodeNames(
          entity.getGuideTeachers().stream().map(TeacherTable::getFullCodeName));
    }

    if (CollectionUtils.isNotEmpty(entity.getStudents())) {
      result.setGuideTeacherCodeNames(
          entity.getStudents().stream().map(StudentTable::getFullCodeName));
    }

    return result;
  }
}
