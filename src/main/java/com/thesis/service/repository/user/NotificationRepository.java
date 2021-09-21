package com.thesis.service.repository.user;

import java.util.List;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends BaseRepository<NotificationTable> {

  @Query(value = "SELECT usN.* FROM us_notification usN "
      + "WHERE usN.receiver_id = ?1 "
      + "ORDER BY usN.id DESC LIMIT ?2", nativeQuery = true)
  List<NotificationTable> findByIdDesc(long receiverId, int limit);

  @Query(value = "SELECT usN.* FROM us_notification usN "
      + "WHERE usN.receiver_id = ?1 AND usN.seen = FALSE "
      + "ORDER BY usN.id DESC LIMIT ?2", nativeQuery = true)
  List<NotificationTable> findByUnseenIdDesc(long receiverId, int limit);

}
