package com.thesis.service.common.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.SerializationUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBaseFieldConst {

  private static Map<String, String> overrideMap(
      Map<String, String> originMap,
      Map<String, String> newMap) {

    return Stream.of(originMap, newMap)
        .flatMap(map -> map.entrySet().stream())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (map1, map2) -> map2));
  }

  private static Map<String, String> put(
      Map<String, String> source,
      String key,
      String value) {
    var result = SerializationUtils.clone(new HashMap<>(source));
    result.put(key, value);
    return result;
  }

  public static final Map<String, String> MODAL = Map.ofEntries(
      Map.entry("guideTeacher", "guideTeacherCode"),
      Map.entry("executeStudent", "executeStudentCode"), Map.entry("topic.major", "topic.majorId"));

  public static final Map<String, String> ENTITY = Map.ofEntries(
      Map.entry("topic.code", "tP.code"),
      Map.entry("topic.semester", "tP.semester"),
      Map.entry("topic.name", "tP.name::::json->>'%s'"),
      Map.entry("topic.major", "bCD_major.value::::json->>'%s'"),
      Map.entry("topic.educationMethod", "bCD_edu_method.value::::json->>'%s'"),
      Map.entry("subjectDepartment", "bCD_subject_department.value::::json->>'%s'"),
      Map.entry("educationMethod", "bCD_edu_method.value::::json->>'%s'"),
      Map.entry("gender", "bCD_gender.value::::json->>'%s'"),
      Map.entry("degree", "bCD_degree.value::::json->>'%s'"),
      Map.entry("major", "bCD_major.value::::json->>'%s'"),
      Map.entry("guideTeacher", "pT.name"),
      Map.entry("executeStudent", "pS.name")

  );

  public static final Map<String, String> TEACHER = Map.ofEntries(
      Map.entry("code", "pT.code"),
      Map.entry("name", "pT.name"),
      Map.entry("email", "pT.email"),
      Map.entry("phone", "pT.phone"));

  public static final Map<String, String> STUDENT = Map.ofEntries(
      Map.entry("code", "psS.code"),
      Map.entry("name", "psS.name"),
      Map.entry("email", "psS.email"),
      Map.entry("phone", "psS.phone")

  );

  public static final Map<String, String> TEACHER_ENTITY =
      DataBaseFieldConst.overrideMap(ENTITY, TEACHER);

  public static final Map<String, String> STUDENT_ENTITY =
      DataBaseFieldConst.overrideMap(ENTITY, STUDENT);

  public static final Map<String, String> TOPIC_ASSIGN_ENTITY =
      DataBaseFieldConst.put(ENTITY, "id", "tPA.id");

}
