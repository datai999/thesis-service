package com.thesis.service.repository.br;

import com.thesis.service.model.br.SettingTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends BaseRepository<SettingTable> {

  @Query(value = "SELECT bS.* FROM br_setting bS LEFT JOIN br_const_data bCD ON bS.name_id = bCD.id"
      + " WHERE bCD.type = :type LIMIT 1", nativeQuery = true)
  SettingTable findByType(@Param("type") String type);

}
