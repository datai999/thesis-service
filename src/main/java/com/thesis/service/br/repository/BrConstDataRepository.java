package com.thesis.service.br.repository;

import java.util.List;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.repository.BaseRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrConstDataRepository extends BaseRepository<BrConstDataTable> {

  @Query(value = "SELECT DISTINCT type FROM br_const_data", nativeQuery = true)
  List<String> findAllType();

}
