package com.thesis.service.constant;

import java.util.Objects;

public enum TopicRole {
  STUDENT, GUIDE_TEACHER, REVIEW_TEACHER, COUNCIL;

  public static TopicRole from(String value) {
    if (Objects.isNull(value))
      return STUDENT;
    switch (value.toLowerCase()) {
      case "guide":
        return GUIDE_TEACHER;
      case "review":
        return REVIEW_TEACHER;
      case "council":
        return COUNCIL;
      default:
        return STUDENT;
    }
  }

}
