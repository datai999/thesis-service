package com.thesis.service.advice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import com.google.firebase.auth.FirebaseAuthException;
import com.thesis.service.dto.WrapResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(FirebaseAuthException.class)
  Object firebaseAuthException(FirebaseAuthException ex) {
    return ResponseEntity.badRequest()
        .body(WrapResponse.error(ex.getAuthErrorCode(), ex.getMessage()));
  }

  @ExceptionHandler(BadCredentialsException.class)
  Object handleBadCredentialsException(BadCredentialsException ex) {
    return ResponseEntity.badRequest().body(WrapResponse.errorCode(ex.getMessage()));
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

  @ExceptionHandler(NoSuchElementException.class)
  ResponseEntity<Object> businessException(NoSuchElementException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(WrapResponse.errorCode("data.notFound"));
  }

  @ExceptionHandler(BusinessException.class)
  ResponseEntity<Object> businessException(BusinessException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(WrapResponse.errorCode(ex.getCode(), ex.getArgs()));
  }

}
