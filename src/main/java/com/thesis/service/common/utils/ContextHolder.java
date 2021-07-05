package com.thesis.service.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContextHolder {

  protected static final Map<String, Object> headerHolder = new HashMap<>();

  public static void setRequest(HttpServletRequest request) {
    headerHolder.put("lang", request.getHeader("Lang"));
  }

  public static String getLang() {
    var lang = headerHolder.get("lang");
    return Objects.nonNull(lang) ? lang.toString() : null;
  }
}
