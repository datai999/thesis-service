package com.thesis.service.repository.user;

import java.util.List;
import java.util.Optional;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserTable> {

  Optional<UserTable> findByEmail(String email);

  @Query(nativeQuery = true,
      value = "SELECT uU.* FROM us_user uU " +
          "WHERE uU.permission = 'STUDENT' " +
          "AND uU.education_method_id IN ?1 " +
          "AND uU.major_id IN ?2")
  List<UserTable> findStudentInEducationMethodAndMajor(
      List<Long> educationMethodIds, List<Long> majorIds);

}
