package com.thesis.service.advice;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import com.thesis.service.dto.WrapResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(BadCredentialsException.class)
  Object handleBadCredentialsException(BadCredentialsException ex) {
    return ResponseEntity.badRequest().body(WrapResponse.error(ex.getMessage()));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

    var msg = ex.getConstraintViolations().stream().map(e -> {
      String[] path = e.getPropertyPath().toString().split("\\.");
      return String.format("%s: %s", path[path.length - 1], e.getMessage());
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(WrapResponse.error(ex.getMostSpecificCause().getStackTrace()[0]));
  }
}
