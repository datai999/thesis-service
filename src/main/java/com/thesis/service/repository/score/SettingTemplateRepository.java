package com.thesis.service.repository.score;

import java.util.List;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.model.score.SettingTemplateTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingTemplateRepository extends BaseRepository<SettingTemplateTable> {

  @Query("SELECT scST FROM SettingTemplateTable scST " +
      "WHERE scST.deleted = FALSE AND scST.thesis = ?1 AND scST.topicRole = ?2")
  List<SettingTemplateTable> findTemplateByTopicRole(boolean thesis, TopicRole topicRole);

}
