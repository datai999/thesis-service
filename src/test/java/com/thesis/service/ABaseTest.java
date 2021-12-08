package com.thesis.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.service.dto.user.CustomUserDetail;
import com.thesis.service.model.user.UserTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Provider context environment for testing
 */
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayNameGeneration(DisplayNameCamelCase.class)
public abstract class ABaseTest {

  protected static ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

  @BeforeAll
  static void beforeAll() {
    setRequestUser(new CustomUserDetail(new UserTable()));
    messageSource.setCacheSeconds(3);
    messageSource.setBasename("messages");
  }

  @BeforeEach
  protected void beforeEach(TestInfo testInfo) {
    System.out.printf("\n\n>>>>>START TEST: %s\n", testInfo.getDisplayName());
  }

  @AfterEach
  protected void afterEach(TestInfo testInfo) {
    System.out.printf(">>>>>>>END TEST: %s", testInfo.getDisplayName());
  }

  protected void hasMessageSourceEachLocale(String code, Object... args) {
    assertDoesNotThrow(() -> {
      System.out.printf("Test message source each locale for code: %s\n", code);
      System.out.printf("%s: %s%n", LocaleContextHolder.getLocale(),
          messageSource.getMessage(code, args, LocaleContextHolder.getLocale()));
    });
  }

  /**
   * Make deep copy for reuse test case but not change the original structure
   *
   * @param input object to be copy
   * @param <T> type of object to be copy
   * @return deep copy version of input
   */
  @SuppressWarnings("unchecked")
  protected <T> T deepCopy(T input) {
    ObjectMapper objectMapper = new ObjectMapper();
    T deepCopyRequestBody = null;
    try {
      deepCopyRequestBody = (T) objectMapper
          .readValue(objectMapper.writeValueAsString(input), input.getClass());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return deepCopyRequestBody;
  }

  /**
   * Set current request user to Security context
   *
   * @param user current request user
   */
  protected static void setRequestUser(CustomUserDetail user) {
    Authentication authentication = Mockito.mock(Authentication.class);
    when(authentication.getPrincipal()).thenReturn(user);

    SecurityContext securityContext = Mockito.mock(SecurityContext.class);
    when(securityContext.getAuthentication()).thenReturn(authentication);

    SecurityContextHolder.setContext(securityContext);
  }

}
