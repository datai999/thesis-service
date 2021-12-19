package com.thesis.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.service.dto.ModelConverter;
import com.thesis.service.dto.system.SemesterResponse;
import com.thesis.service.dto.user.CustomUserDetail;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.service.MessageSourceService;
import com.thesis.service.service.system.SemesterService;
import com.thesis.service.utils.ContextAccessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ABaseTest {

  protected MessageSourceService messageSource;
  protected ModelConverter mapper;

  protected MockedStatic<ContextAccessor> contextAccessor;

  @BeforeAll
  void beforeAll() {
    setRequestUser(new CustomUserDetail(new UserTable()));

    var resourceBundleMessageSource = new ResourceBundleMessageSource();
    resourceBundleMessageSource.setCacheSeconds(3);
    resourceBundleMessageSource.setBasename("messages");
    this.messageSource = new MessageSourceService(resourceBundleMessageSource);
    this.mapper = spy(new ModelConverter());

    var semesterService = mock(SemesterService.class);
    when(semesterService.getCurrentSemester()).thenReturn(new SemesterResponse());

    this.contextAccessor = Mockito.mockStatic(ContextAccessor.class);
    this.contextAccessor.when(ContextAccessor::getModelConverter).thenReturn(this.mapper);
    this.contextAccessor.when(ContextAccessor::getMessageSource).thenReturn(this.messageSource);
    this.contextAccessor.when(() -> ContextAccessor.getBean(SemesterService.class))
        .thenReturn(semesterService);
  }

  @AfterAll
  void afterAll() {
    contextAccessor.close();
  }

  @BeforeEach
  protected void beforeEach(TestInfo testInfo) {
    System.out.printf("\n\n>>>>>START TEST: %s\n", testInfo.getDisplayName());
  }

  @AfterEach
  protected void afterEach(TestInfo testInfo) {
    System.out.printf(">>>>>>>END TEST: %s", testInfo.getDisplayName());
  }

  protected String getMessage(String code, Object... args) {
    return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
  }

  protected void hasMessageSourceEachLocale(String code, Object... args) {
    assertDoesNotThrow(() -> {
      System.out.printf("Test message source each locale for code: %s\n", code);
      System.out.printf("%s: %s%n", LocaleContextHolder.getLocale(),
          this.getMessage(code, args, LocaleContextHolder.getLocale()));
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
