package com.thesis.service.repository.topic;

import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilMemberRepository extends BaseRepository<CouncilMemberTable> {

  @Modifying
  @Query("DELETE FROM CouncilMemberTable tCM WHERE tCM.council.id=?1")
  void removeAllMember(long councilId);

}
