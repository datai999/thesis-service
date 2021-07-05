package com.thesis.service.common.dto;

import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBaseFieldConst {

  public static final Map<String, String> MODAL = Map.ofEntries(
      Map.entry("guideTeacher", "guideTeacherCode"),
      Map.entry("executeStudent", "executeStudentCode"), Map.entry("topic.major", "topic.majorId"));

  public static final Map<String, String> ENTITY = Map.ofEntries(
      Map.entry("topic.code", "tP.code"),
      Map.entry("topic.semester", "tP.semester"),
      Map.entry("topic.name", "tP.name::::json->>'%s'"),
      Map.entry("topic.major", "bCD_major.value::::json->>'%s'"),
      Map.entry("topic.educationMethod", "bCD_edu_method.value::::json->>'%s'"),
      Map.entry("guideTeacher", "pT.name"),
      Map.entry("executeStudent", "pS.name")

  );

  public static final Map<String, String> TEACHER = Map.ofEntries(
      Map.entry("code", "pT.code")

  );

  public static final Map<String, String> TEACHER_ENTITY = Map.copyOf(TEACHER);

  @PostConstruct
  private void initMap() {
    TEACHER_ENTITY.putAll(ENTITY);
  }

}
