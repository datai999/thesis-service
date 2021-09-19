package com.thesis.service.repository.system;

import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends BaseRepository<SemesterTable> {

  @Query("SELECT s FROM SemesterTable s WHERE s.status = 'USING'")
  SemesterTable findCurrentSemester();

}
