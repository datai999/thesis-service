package com.thesis.service.repository.score;

import java.util.List;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.model.score.CriterionRoleTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterionRoleRepository extends BaseRepository<CriterionRoleTable> {

  @Query("SELECT scCR FROM CriterionRoleTable scCR " +
      "WHERE scCR.deleted = FALSE AND scCR.thesis = ?1 AND scCR.topicRole = ?2")
  List<CriterionRoleTable> findTopicRoleCriterion(boolean thesis, TopicRole topicRole);

}
