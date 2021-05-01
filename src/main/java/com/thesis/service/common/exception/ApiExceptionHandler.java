package com.thesis.service.common.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

    var msg = ex.getConstraintViolations().stream().map(e -> {
      String[] path = e.getPropertyPath().toString().split("\\.");
      return String.format("%s: %s", path[path.length - 1], e.getMessage());
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
  }
}
