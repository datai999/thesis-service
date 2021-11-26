package com.thesis.service.repository.score;

import java.util.List;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends BaseRepository<TemplateTable> {

  @Query("SELECT DISTINCT sT FROM TemplateTable sT " +
      "INNER JOIN sT.councilRoles sCR " +
      "WHERE sCR.id = ?1 ")
  List<TemplateTable> getByCouncilMember(long roleId);

}
