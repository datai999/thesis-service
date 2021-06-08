package com.thesis.service.topic.repository;

import java.util.List;

import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.topic.model.TpTopicAssignTable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TpTopicAssignRepository extends BaseRepository<TpTopicAssignTable> {

  @Query(value = "SELECT * FROM tp_topic_assign"
      + " WHERE :teacherCode = ANY(guide_teacher_code) OR :teacherCode = ANY(review_teacher_code)", nativeQuery = true)
  List<TpTopicAssignTable> findByTeacherCode(@Param("teacherCode") String teacherCode);
}
