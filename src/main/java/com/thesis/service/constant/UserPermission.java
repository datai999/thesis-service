package com.thesis.service.constant;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserPermission {
  STUDENT("STUDENT"), TEACHER("TEACHER"), HEAD_SUBJECT_DEPARTMENT(
      "HEAD_SUBJECT_DEPARTMENT"), EDUCATION_STAFF("EDUCATION_STAFF"), ADMIN("ADMIN");

  private String value;

  public static UserPermission of(String value) {
    return Stream.of(UserPermission.values())
        .filter(e -> e.getValue().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
