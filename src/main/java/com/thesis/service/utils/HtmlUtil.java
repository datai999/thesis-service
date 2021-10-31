package com.thesis.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class HtmlUtil {

  private static String HOST;

  @Value("${host}")
  public void setNameStatic(String host) {
    HtmlUtil.HOST = host;
  }

  public static String toATag(String href, String innerValue) {
    return String.format("<a target=\"_blank\" href=\"%s%s\">%s</a>", HOST, href, innerValue);
  }

}
