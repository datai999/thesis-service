package com.thesis.service.dto.person.response;

import java.util.Objects;
import com.thesis.service.model.person.PsTeacherTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherFlatResponse extends PsTeacherTable {

  private String gender;
  private String subjectDepartmentName;
  private String degreeName;

  public static TeacherFlatResponse from(PsTeacherTable entity) {

    var result = ContextAccessor.getModelMapper()
        .map(entity, TeacherFlatResponse.class)
        .setGender(entity.getMale() ? "Nam" : "Nữ");

    if (Objects.nonNull(entity.getSubjectDepartment())
        && Objects.nonNull(entity.getSubjectDepartment().getName())) {
      result.setSubjectDepartmentName(entity.getSubjectDepartment().getName().getVi());
    }

    if (Objects.nonNull(entity.getDegree()) && Objects.nonNull(entity.getDegree().getName())) {
      result.setDegreeName(entity.getDegree().getName().getVi());
    }

    return result;
  }
}
