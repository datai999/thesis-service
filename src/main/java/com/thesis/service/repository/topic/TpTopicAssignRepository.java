package com.thesis.service.repository.topic;

import java.util.List;
import com.thesis.service.model.topic.TpTopicAssignTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TpTopicAssignRepository extends BaseRepository<TpTopicAssignTable> {

  @Query(value = TpQueryClause.TOPIC_ASSIGN_INNER_JOIN_TOPIC
      + "WHERE :teacherCode = ANY(guide_teacher_code) OR :teacherCode = ANY(review_teacher_code) ORDER BY"
      + TpQueryClause.ORDER_TOPIC_SEMESTER, nativeQuery = true)
  List<TpTopicAssignTable> findByTeacherCode(
      @Param("teacherCode") String teacherCode,
      @Param("sort") String sort,
      @Param("isDescend") Boolean isDescend);

  @Query(
      value = TpQueryClause.TOPIC_ASSIGN_INNER_JOIN_TOPIC
          + "WHERE tP.name ILIKE %:value% ORDER BY"
          + TpQueryClause.ORDER_TOPIC_SEMESTER,
      nativeQuery = true)
  List<TpTopicAssignTable> searchIlikeTopicName(
      @Param("value") String value,
      @Param("sort") String sort,
      @Param("isDescend") Boolean isDescend);

  @Query(
      value = "SELECT * FROM tp_topic_assign tTA WHERE tTA.topic_id = :topicId ORDER BY tTA.semester",
      nativeQuery = true)
  List<TpTopicAssignTable> findByTopicIdOrderSemester(@Param("topicId") long topicId);

}
