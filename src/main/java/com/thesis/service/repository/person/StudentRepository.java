package com.thesis.service.repository.person;

import java.util.List;
import com.thesis.service.model.person.StudentTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends BaseRepository<StudentTable> {

  @Query(value = "SELECT * FROM ps_student "
      + "WHERE name ILIKE %:value% OR CAST(code AS VARCHAR) ILIKE %:value%", nativeQuery = true)
  List<StudentTable> searchIlikeNameOrCode(@Param("value") String value);

  @Query(value = "SELECT * FROM ps_student WHERE code IN (:codes)", nativeQuery = true)
  List<StudentTable> findAllByCode(@Param("codes") Iterable<String> codes);
}
