package com.thesis.service.common.dto;

import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBaseFieldConst {

  public static final Map<String, String> map = Map.ofEntries(Map.entry("guideTeacher", "guideTeacherCode"),
      Map.entry("executeStudent", "executeStudentCode"), Map.entry("topic.major", "topic.majorId"));

}
