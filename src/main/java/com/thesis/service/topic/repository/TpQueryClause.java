package com.thesis.service.topic.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TpQueryClause {

  public static final String TOPIC_ASSIGN_INNER_JOIN_TOPIC =
      "SELECT tPA.* FROM tp_topic_assign tPA INNER JOIN tp_topic tP ON tPA.topic_id = tP.id ";

  public static final String TOPIC_ASSIGN_SEARCH = TOPIC_ASSIGN_INNER_JOIN_TOPIC
      + "LEFT JOIN ps_teacher pT ON pT.code =  ANY(tPA.guide_teacher_code) "
      + "LEFT JOIN br_const_data bCD_major ON bCD_major.id = ANY(tP.major_id) "
      + "LEFT JOIN br_const_data bCD_edu_method ON bCD_edu_method.id = tP.education_method_id "

  ;

  public static final String ORDER_TOPIC_SEMESTER =
      " CASE WHEN :sort = 'semester' AND :isDescend THEN tP.semester END DESC"
          + " ,CASE WHEN :sort = 'semester' AND NOT :isDescend THEN tP.semester END ASC ";

}
