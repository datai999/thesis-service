package com.thesis.service.br.repository;

import java.util.List;

import com.thesis.service.br.dto.TypeValueDto;
import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.repository.BaseRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrConstDataRepository extends BaseRepository<BrConstDataTable> {

  @Query(value = "SELECT type, ARRAY_AGG(value) AS arrValue " + "FROM br_const_data "
      + "GROUP BY type", nativeQuery = true)
  List<TypeValueDto> findAllType();

}
