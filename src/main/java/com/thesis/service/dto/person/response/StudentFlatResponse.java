package com.thesis.service.dto.person.response;

import java.util.Objects;
import com.thesis.service.model.person.StudentTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentFlatResponse extends StudentTable {

  private String gender;
  private String educationMethodName;
  private String majorName;

  public static StudentFlatResponse from(StudentTable entity) {

    var result = ContextAccessor.getModelMapper()
        .map(entity, StudentFlatResponse.class)
        .setGender(entity.getMale() ? "Nam" : "Nữ");

    if (Objects.nonNull(entity.getEducationMethod())) {
      result.setEducationMethodName(entity.getEducationMethod().getName());
    }

    if (Objects.nonNull(entity.getEducationMethod())
        && Objects.nonNull(entity.getEducationMethod().getName())) {
      result.setMajorName(entity.getMajor().getName().getVi());
    }

    return result;
  }
}
