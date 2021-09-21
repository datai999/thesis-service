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
      value = "WITH "

          + "topic_assign AS ( "
          + "SELECT tpTA.guide_teacher_id, tpTA.review_teacher_id, tpTA.student_id "
          + "FROM tp_topic_assign tpTA "
          + "INNER JOIN tp_topic tpT ON tpT.id = tpTA.topic_id "
          + "INNER JOIN sy_semester syS ON syS.id = tpT.semester_id AND syS.status = 'USING') "

          + ",user_id AS ( "
          + "SELECT guide_teacher_id AS id FROM topic_assign "
          + "UNION SELECT review_teacher_id FROM topic_assign "
          + "UNION SELECT student_id FROM topic_assign) "

          + "INSERT INTO us_notification (receiver_id, message) "
          + "SELECT id, ?1 FROM user_id WHERE user_id.id IS NOT NULL")
  void notifyUserHasTopicInCurrentSemester(String message);

}
