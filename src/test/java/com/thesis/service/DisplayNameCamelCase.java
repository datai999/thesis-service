package com.thesis.service;

import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayNameGenerator;

public class DisplayNameCamelCase extends DisplayNameGenerator.Standard {

  @Override
  public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
    return this.replaceCamelCase(testMethod.getName()) +
        DisplayNameGenerator.parameterTypesAsString(testMethod);
  }

  String replaceCamelCase(String name) {
    StringBuilder result = new StringBuilder();
    for (char c : name.toCharArray()) {
      if (Character.isUpperCase(c)) {
        result.append(' ');
        result.append(Character.toLowerCase(c));
      } else {
        result.append(c);
      }
    }
    return result.toString();
  }

}
