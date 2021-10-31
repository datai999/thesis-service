package com.thesis.service.repository.user;

import java.util.List;
import com.thesis.service.constant.UserType;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserTable> {

  UserTable findByEmail(String email);

  List<UserTable> findByType(UserType type, Sort sort);

}
