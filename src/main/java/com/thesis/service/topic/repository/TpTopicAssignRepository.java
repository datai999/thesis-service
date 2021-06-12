package com.thesis.service.topic.repository;

import java.util.List;

import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.topic.model.TpTopicAssignTable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TpTopicAssignRepository extends BaseRepository<TpTopicAssignTable> {

  @Query(value = "SELECT tPA.* FROM tp_topic_assign tPA INNER JOIN tp_topic tP ON tPA.topic_id = tP.id"
      + " WHERE :teacherCode = ANY(guide_teacher_code) OR :teacherCode = ANY(review_teacher_code)"
      + " ORDER BY CASE WHEN :sort = 'semester' AND :isDescend THEN semester END DESC"
      + " ,CASE WHEN :sort = 'semester' AND NOT :isDescend THEN semester END ASC", nativeQuery = true)
  List<TpTopicAssignTable> findByTeacherCode(@Param("teacherCode") String teacherCode, @Param("sort") String sort,
      @Param("isDescend") Boolean isDescend);
}
