package com.thesis.service.dto.user.response;

import com.thesis.service.constant.UserPermission;
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

  private Boolean male;

  private String gender;

  private UserPermission permission;

  private String educationMethodName;

  private BaseResponse educationMethod;

  private String majorName;

  private BaseResponse major;

  private String subjectDepartmentName;

  private BaseResponse subjectDepartment;

  private String degreeName;

  private BaseResponse degree;

}
