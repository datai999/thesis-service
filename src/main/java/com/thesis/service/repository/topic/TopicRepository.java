package com.thesis.service.repository.topic;

import java.util.List;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends BaseRepository<TopicTable> {

  @Query("SELECT tT FROM TopicTable tT " +
      "WHERE tT.semester.status = 'USING' " +
      "AND tT.subjectDepartment.id = ?1 " +
      "AND tT.council IS NULL")
  List<TopicTable> findNeedAssignCouncil(long subjectDepartmentId, Sort sort);

}
