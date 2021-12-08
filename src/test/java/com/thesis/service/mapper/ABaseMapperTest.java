package com.thesis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import com.thesis.service.ABaseTest;

/**
 * Provider context environment for mapper testing
 */
public abstract class ABaseMapperTest extends ABaseTest {

  protected void assertFields(Object expected, Object actual, String... ignoreFields) {
    assertThat(actual)
        .usingRecursiveComparison()
        .ignoringFields(ignoreFields)
        .isEqualTo(expected);
  }

  protected void assertFieldsExceptCreatedUpdated(Object expected, Object actual) {
    this.assertFields(expected, actual, "createdAt", "updatedAt");
  }

}
