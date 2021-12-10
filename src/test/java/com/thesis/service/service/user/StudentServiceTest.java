package com.thesis.service.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseServiceTest;
import com.thesis.service.service.topic.TopicService;
import com.thesis.service.service.user.testsuite.StudentServiceTS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

public class StudentServiceTest extends ABaseServiceTest {

  @InjectMocks
  StudentService service;
  @Mock
  UserRepository userRepository;
  @Mock
  TopicService topicService;

  @BeforeEach
  void beforeEach() {
    this.userRepository = mock(UserRepository.class);
    ReflectionTestUtils.setField(this.service, "userRepository", userRepository);
  }

  @ParameterizedTest(name = "Test {index}: Language property file contain message code -> {0}")
  @ArgumentsSource(StudentServiceTS.UseMessageCode.class)
  void hasMessageSource(String messageCode) {
    super.hasMessageSourceEachLocale(messageCode);
  }

  @Test
  @DisplayName("Throw error when not found user")
  void userNotFound() {
    when(userRepository.findById(StudentServiceTS.INVALID_USER_ID))
        .thenThrow(new NoSuchElementException());
    Exception exception = assertThrows(NoSuchElementException.class,
        () -> service.getTopic(StudentServiceTS.INVALID_USER_ID));
    assertEquals(null, exception.getMessage());
  }

  @Test
  @DisplayName("Student dont have topic execute")
  void studentDontHaveTopicExecute() {
    when(userRepository.findById(StudentServiceTS.VALID_USER_ID))
        .thenReturn(Optional.of(new UserTable().setTopicExecutes(List.of())));
    assertEquals(null, service.getTopic(StudentServiceTS.VALID_USER_ID));
  }

}
