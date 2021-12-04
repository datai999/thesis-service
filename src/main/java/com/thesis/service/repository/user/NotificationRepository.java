package com.thesis.service.repository.user;

import java.util.List;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends BaseRepository<NotificationTable> {

  @Query(nativeQuery = true,
      value = "SELECT usN.* FROM us_notification usN "
          + "WHERE usN.receiver_id = ?1 "
          + "ORDER BY usN.id DESC LIMIT ?2")
  List<NotificationTable> findByIdDesc(long receiverId, int limit);

  @Query(nativeQuery = true,
      value = "SELECT usN.* FROM us_notification usN "
          + "WHERE usN.receiver_id = ?1 AND usN.seen = FALSE "
          + "ORDER BY usN.id DESC LIMIT ?2")
  List<NotificationTable> findByUnseenIdDesc(long receiverId, int limit);

  @Query(nativeQuery = true,
      value = "WITH " +
          "current_semester AS ( " +
          "SELECT * " +
          "FROM sy_semester " +
          "WHERE status = 'USING' )" +

          ", topic AS (" +
          "SELECT tT.* " +
          "FROM tp_topic tT " +
          "INNER JOIN current_semester ON current_semester.id = tT.semester_id ) " +

          ", student_message AS ( " +
          "SELECT DISTINCT tS.student_id AS receiver_id, ?1 " +
          "FROM tp_student tS " +
          "INNER JOIN topic ON topic.id = tS.topic_id ) " +

          ", guide_teacher_message AS (" +
          "SELECT DISTINCT tGT.guide_teacher_id AS receiver_id, ?2 " +
          "FROM tp_guide_teacher tGT " +
          "INNER JOIN topic ON topic.id = tGT.topic_id ) " +

          "INSERT INTO us_notification (receiver_id, message) " +
          "SELECT * FROM student_message " +
          "UNION " +
          "SELECT * FROM guide_teacher_message")
  void notifyUserHasTopicInCurrentSemester(String userMessage, String teacherMessage);

}
