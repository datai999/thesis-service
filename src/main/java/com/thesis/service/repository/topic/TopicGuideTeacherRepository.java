package com.thesis.service.repository.topic;

import com.thesis.service.model.topic.TopicGuideTeacherTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicGuideTeacherRepository extends BaseRepository<TopicGuideTeacherTable> {

  @Modifying
  @Query("DELETE FROM TopicGuideTeacherTable tGT WHERE tGT.topic.id = ?1")
  void deleteByTopic(long topicId);

}
