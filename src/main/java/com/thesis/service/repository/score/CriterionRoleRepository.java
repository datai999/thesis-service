package com.thesis.service.repository.score;

import java.util.List;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.model.score.CriterionRoleTable;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterionRoleRepository extends BaseRepository<CriterionRoleTable> {

  @Query("SELECT scCR.template FROM CriterionRoleTable scCR " +
      "WHERE scCR.thesis = ?1 AND scCR.topicRole = ?2")
  List<CriterionTable> findTopicRoleCriterion(boolean thesis, TopicRole topicRole);

  // @Query(nativeQuery = true,
  // value = "SELECT scCR.template_id " +
  // "FROM tp_council_role tpCR " +
  // "LEFT JOIN sc_criterion_role scCR ON tpCR.id = scCR.council_role_id " +
  // "WHERE tpCR.deleted = FALSE " +
  // "ORDER BY tpCR.display_order")
  // List<CriterionTable> findCouncilRoleCriterion();

}
