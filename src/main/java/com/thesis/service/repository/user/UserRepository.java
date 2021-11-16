package com.thesis.service.repository.user;

import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserTable> {

  UserTable findByEmail(String email);

}
