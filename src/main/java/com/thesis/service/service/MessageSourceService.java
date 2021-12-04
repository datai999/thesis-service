package com.thesis.service.service;

import javax.annotation.PostConstruct;
import com.thesis.service.config.firebase.FirebaseAuthenticationToken;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageSourceService {

  @Value("${host}")
  private String HOST;

  private String myTopicView;

  private final MessageSource messageSource;

  @PostConstruct
  public void postConstruct() {
    this.myTopicView = this.getMessage(MessageCode.My.TOPIC_VIEW);
  }

  public String getMessage(String code, Object... args) {
    return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
  }

  public String requestUserUpdate(String message) {
    var auth = FirebaseAuthenticationToken.class
        .cast(SecurityContextHolder.getContext().getAuthentication());
    var requestUserATag = this.toATag("/#", auth.getPrincipal().getFullName());
    return this.getMessage("user.update", requestUserATag, message);
  }

  public String toATag(CouncilTable council) {
    var href = String.format("/councils/%s", council.getSubjectDepartment().getId());
    var councilMessage = this.getMessage(MessageCode.Council.CODE, council.getId());
    return this.toATag(href, councilMessage);
  }

  public String toUserTag(UserTable user) {
    return String.format("<user id=%s >%s</user>", user.getId(), user.getFullName());
  }

  public String toTopicTag(TopicTable topic) {
    return String.format("<topic id=%s >đề tài mã số %s</topic>", topic.getId(), topic.getId());
  }

  public String toATag(String href, String innerValue) {
    return String.format("<newTab href=\"%s\" >%s</newTab>", href, innerValue);
  }

  public String dashboardTag(String innerValue) {
    return String.format("<newTab href=\"/dashboard\" >%s</newTab>", innerValue);
  }

  public String topicExecuteTag() {
    return String.format("<newTab href=\"/execute\" >%s</newTab>", this.myTopicView);
  }

  public String topicGuideTag() {
    return String.format("<newTab href=\"/guide\" >%s</newTab>", this.myTopicView);
  }

}
