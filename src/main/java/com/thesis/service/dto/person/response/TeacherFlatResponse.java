package com.thesis.service.dto.person.response;

import java.util.Objects;
import com.thesis.service.model.person.PersonTable;
import com.thesis.service.model.person.TeacherTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherFlatResponse extends PersonTable {

  private String gender;
  private String subjectDepartmentName;
  private String degreeName;

  public static TeacherFlatResponse from(TeacherTable entity) {

    var result = ContextAccessor.getModelMapper()
        .map(entity, TeacherFlatResponse.class)
        .setGender(entity.getPerson().isMale() ? "Nam" : "Nữ");

    if (Objects.nonNull(entity.getSubjectDepartment())) {
      result.setSubjectDepartmentName(entity.getSubjectDepartment().getName());
    }

    if (Objects.nonNull(entity.getDegree())) {
      result.setDegreeName(entity.getDegree().getName());
    }

    return result;
  }
}
