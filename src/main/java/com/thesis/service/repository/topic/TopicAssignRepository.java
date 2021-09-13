package com.thesis.service.repository.topic;

import java.util.List;
import javax.transaction.Transactional;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.model.user.UserTable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicAssignRepository
    extends JpaRepository<TopicAssignTable, Long>, JpaSpecificationExecutor<TopicAssignTable> {

  List<TopicAssignTable> findByStudent(UserTable guideTeacher, Sort sort);

  List<TopicAssignTable> findByGuideTeacher(UserTable guideTeacher, Sort sort);

  List<TopicAssignTable> findByReviewTeacher(UserTable guideTeacher, Sort sort);

  @Transactional
  @Modifying
  @Query("DELETE FROM TopicAssignTable TA WHERE TA.topic.id=?1 AND TA.student.id=?2")
  void studentCancel(Long topicId, Long studentId);

}
