package com.thesis.service.repository.topic;

import java.time.LocalDate;
import java.util.List;
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

  @Query(nativeQuery = true,
      value = "SELECT tCM.* " +
          "FROM tp_council_member tCM " +
          "INNER JOIN tp_council tC ON tC.id = tCM.council_id " +
          "WHERE tCM.member_id IN ?1 " +
          "AND tCM.council_id != ?2 " +
          "AND tC.reserve_date = ?3")
  List<CouncilMemberTable> getConflictTimeLine(List<Long> memberIds, long councilId,
      LocalDate reserveDate);

}
