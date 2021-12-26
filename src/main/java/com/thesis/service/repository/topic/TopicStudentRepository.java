package com.thesis.service.repository.topic;

import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicStudentRepository extends BaseRepository<TopicStudentTable> {

  @Modifying
  @Query("DELETE FROM TopicStudentTable tS WHERE tS.topic.id = ?1")
  void deleteByTopic(long topicId);

}
