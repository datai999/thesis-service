package com.thesis.service.topic.repository;

public interface TpQueryClause {

  String TOPIC_ASSIGN_INNER_JOIN_TOPIC = "SELECT tTA.* FROM tp_topic_assign tTA INNER JOIN tp_topic tP ON tTA.topic_id = tP.id";

  String ORDER_TOPIC_SEMESTER = " CASE WHEN :sort = 'semester' AND :isDescend THEN tP.semester END DESC"
      + " ,CASE WHEN :sort = 'semester' AND NOT :isDescend THEN tP.semester END ASC";

}
