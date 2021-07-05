package com.thesis.service.common.dto;

import java.util.Map;
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
      Map.entry("guideTeacher", "pT.name")

  );

}
