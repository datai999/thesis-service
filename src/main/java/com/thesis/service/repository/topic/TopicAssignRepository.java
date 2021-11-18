package com.thesis.service.repository.topic;

import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicAssignRepository extends BaseRepository<TopicAssignTable> {

  @Modifying
  @Query("DELETE FROM TopicAssignTable tA WHERE tA.topic.id=?1 AND tA.reviewTeacher.id != NULL")
  void removeAllReview(Long topicId);

}
