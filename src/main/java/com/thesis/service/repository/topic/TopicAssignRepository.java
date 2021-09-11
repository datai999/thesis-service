// package com.thesis.service.repository.topic;

// import java.util.List;
// import com.thesis.service.model.topic.TopicAssignTable;
// import com.thesis.service.repository.BaseRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// // @Repository
// public interface TopicAssignRepository extends BaseRepository<TopicAssignTable> {

//   @Query(value = QueryClause.TOPIC_ASSIGN_INNER_JOIN_TOPIC
//       + "WHERE :teacherCode = ANY(guide_teacher_code) OR :teacherCode = ANY(review_teacher_code) ORDER BY"
//       + QueryClause.ORDER_TOPIC_SEMESTER, nativeQuery = true)
//   List<TopicAssignTable> findByTeacherCode(
//       @Param("teacherCode") String teacherCode,
//       @Param("sort") String sort,
//       @Param("isDescend") Boolean isDescend);

//   @Query(
//       value = QueryClause.TOPIC_ASSIGN_INNER_JOIN_TOPIC
//           + "WHERE tP.name ILIKE %:value% ORDER BY"
//           + QueryClause.ORDER_TOPIC_SEMESTER,
//       nativeQuery = true)
//   List<TopicAssignTable> searchIlikeTopicName(
//       @Param("value") String value,
//       @Param("sort") String sort,
//       @Param("isDescend") Boolean isDescend);

//   @Query(
//       value = "SELECT * FROM tp_topic_assign tTA WHERE tTA.topic_id = :topicId ORDER BY tTA.semester",
//       nativeQuery = true)
//   List<TopicAssignTable> findByTopicIdOrderSemester(@Param("topicId") long topicId);

// }
