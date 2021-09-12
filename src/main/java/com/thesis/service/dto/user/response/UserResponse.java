package com.thesis.service.dto.user.response;

import java.util.Set;
import com.thesis.service.constant.UserType;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;

@Data
public class UserResponse {

  private Long id;

  private String code;

  private String firstName;

  private String lastName;

  private String email;

  private String phone;

  private String gender;

  private Set<String> roles;

  private UserType type;

  private String educationMethodName;

  private String majorName;

  private String subjectDepartmentName;

  private String degreeName;

  public UserResponse(UserTable entity) {
    ContextAccessor.getModelMapper().map(entity, this);
  }

}
