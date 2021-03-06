package com.thesis.service.advice;

import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

  private String code;
  private Object[] args;

  public static BusinessException code(String code, Object... args) {
    return new BusinessException(code, args);
  };

  public static Supplier<BusinessException> codeSupplier(String code, Object... args) {
    return () -> new BusinessException(code, args);
  };

}
