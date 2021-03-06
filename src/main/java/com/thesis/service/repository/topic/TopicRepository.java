package com.thesis.service.repository.topic;

import java.util.List;
import java.util.Set;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends BaseRepository<TopicTable> {

  @Query("SELECT DISTINCT tT FROM TopicTable tT " +
      "INNER JOIN tT.students uS " +
      "INNER JOIN tT.reviewTeachers " +
      "WHERE tT.semester.status = 'USING' " +
      "AND tT.subjectDepartment.id = ?1 " +
      "AND tT.thesis IS TRUE " +
      "AND tT.council IS NULL " +
      "AND uS.midPass IS TRUE")
  List<TopicTable> findNeedAssignCouncil(long subjectDepartmentId, Sort sort);

  @Modifying
  @Query("UPDATE TopicTable tT SET tT.council = ?1 WHERE tT.id IN ?2")
  void updateCouncil(CouncilTable council, Set<Long> topicIds);

}
