package com.thesis.service.service.user;


import static org.mockito.Mockito.spy;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.AEntityServiceTest;

public class UserServiceTest
    extends AEntityServiceTest<UserTable, UserRepository, UserService> {

  @Override
  protected UserService spyService() {
    return spy(new UserService());
  }

}
