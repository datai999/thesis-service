package com.thesis.service.repository.score;

import java.util.List;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends BaseRepository<TemplateTable> {

  @Query("SELECT scT FROM TemplateTable scT " +
      "WHERE scT.deleted = FALSE AND scT.thesis = ?1 AND scT.topicRole = ?2")
  List<TemplateTable> findTopicRoleCriterion(boolean thesis, TopicRole topicRole);

}
