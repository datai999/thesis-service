package com.thesis.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Tag;

public interface AnnotationsTest {

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @Tag("passed")
  @interface Passed {
  }

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @Tag("failed")
  @interface Failed {
  }

}
