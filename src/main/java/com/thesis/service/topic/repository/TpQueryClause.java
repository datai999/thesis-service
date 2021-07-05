package com.thesis.service.topic.repository;

public interface TpQueryClause {

  String TOPIC_ASSIGN_INNER_JOIN_TOPIC =
      "SELECT tPA.* FROM tp_topic_assign tPA INNER JOIN tp_topic tP ON tPA.topic_id = tP.id ";

  String TOPIC_ASSIGN_INNER_JOIN_TOPIC_LEFT_JOIN_TEACHER = TOPIC_ASSIGN_INNER_JOIN_TOPIC
      + "LEFT JOIN ps_teacher pT ON pT.code =  ANY(tPA.guide_teacher_code) ";

  String ORDER_TOPIC_SEMESTER =
      " CASE WHEN :sort = 'semester' AND :isDescend THEN tP.semester END DESC"
          + " ,CASE WHEN :sort = 'semester' AND NOT :isDescend THEN tP.semester END ASC ";

}
