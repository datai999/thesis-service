package com.thesis.service.dto.user.response;

import java.util.List;
import com.thesis.service.constant.UserPermission;
import com.thesis.service.constant.UserType;
import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;

@Data
public class UserResponse {

  private Long id;

  private String code;

  private String firstName;

  private String lastName;

  private String fullName;

  private String email;

  private String phone;

  private String gender;

  private List<UserPermission> permissions;

  private UserType type;

  private String educationMethodName;

  private String majorName;

  private String subjectDepartmentName;

  private BaseResponse subjectDepartment;

  private String degreeName;

}
