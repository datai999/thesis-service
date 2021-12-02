package com.thesis.service.repository.user;

import java.util.Optional;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserTable> {

  Optional<UserTable> findByEmail(String email);

}
