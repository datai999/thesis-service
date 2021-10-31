package com.thesis.service.constant;

public enum PersonRole {

  STUDENT("STUDENT"), TEACHER("TEACHER");

  public final String value;

  private PersonRole(String value) {
    this.value = value;
  }
}
