package com.thesis.service.service.user;

import com.thesis.service.AnnotationsTest.Passed;
import com.thesis.service.service.ABaseServiceTest;
import com.thesis.service.service.user.testsuite.StudentServiceTS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class StudentServiceTest extends ABaseServiceTest {

  @Passed
  @ParameterizedTest(name = "Test {index}: Language property file contain message code -> {0}")
  @ArgumentsSource(StudentServiceTS.UseMessageCode.class)
  void hasMessageSource(String messageCode) {
    super.hasMessageSourceEachLocale(messageCode);
  }

}
