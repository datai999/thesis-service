package com.thesis.service.person.repository;

import java.util.List;

import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.person.model.PsStudentTable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PsStudentRepository extends BaseRepository<PsStudentTable> {

  @Query(value = "SELECT * FROM ps_student "
      + "WHERE name ILIKE %:value% OR CAST(code AS VARCHAR) ILIKE %:value%", nativeQuery = true)
  List<PsStudentTable> searchIlikeNameOrCode(@Param("value") String value);

  @Query(value = "SELECT * FROM ps_student WHERE code IN (:codes)", nativeQuery = true)
  List<PsStudentTable> findAllByCode(@Param("codes") Iterable<String> codes);
}
