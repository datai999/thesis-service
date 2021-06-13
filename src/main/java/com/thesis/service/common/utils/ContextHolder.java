package com.thesis.service.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class ContextHolder {

  public final static Map<String, Object> headerHolder = new HashMap<>();

  public static void setRequest(HttpServletRequest request) {
    headerHolder.put("lang", request.getHeader("Lang"));
  }

  public static String getLang() {
    var lang = headerHolder.get("lang");
    return Objects.nonNull(lang) ? lang.toString() : null;
  }
}
