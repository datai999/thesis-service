package com.thesis.service.utils;

import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
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

  public static String toUserTag(UserTable user) {
    return String.format("<user id=%s>%s</user>", user.getId(), user.getFullName());
  }

  public static String toTopicTag(TopicTable topic) {
    return String.format("<topic id=%s>đề tài mã số %s</topic>", topic.getId(), topic.getId());
  }

}
