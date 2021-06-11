package com.thesis.service.br.repository;

import com.thesis.service.br.model.BrSettingTable;
import com.thesis.service.common.repository.BaseRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrSettingRepository extends BaseRepository<BrSettingTable> {

  @Query(value = "SELECT bS.* FROM br_setting bS LEFT JOIN br_const_data bCD ON bS.name_id = bCD.id"
      + " WHERE bCD.type = :type LIMIT 1", nativeQuery = true)
  BrSettingTable findByType(@Param("type") String type);

}
