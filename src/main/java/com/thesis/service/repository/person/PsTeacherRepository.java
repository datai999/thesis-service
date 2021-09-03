package com.thesis.service.repository.person;

import java.util.List;
import com.thesis.service.model.person.PsTeacherTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PsTeacherRepository extends BaseRepository<PsTeacherTable> {

  @Query(value = "SELECT * FROM ps_teacher WHERE name ILIKE %:value%", nativeQuery = true)
  List<PsTeacherTable> searchIlikeName(@Param("value") String value);

  @Query(value = "SELECT * FROM ps_teacher WHERE code IN (:codes)", nativeQuery = true)
  List<PsTeacherTable> findAllByCode(@Param("codes") Iterable<String> codes);
}
