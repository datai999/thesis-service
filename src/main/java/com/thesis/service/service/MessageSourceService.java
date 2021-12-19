package com.thesis.service.service;

import java.util.Objects;
import javax.annotation.PostConstruct;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.dto.user.CustomUserDetail;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.utils.ContextAccessor;
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

  public String toUserTag(UserTable user) {
    if (Objects.isNull(user))
      user = new UserTable();
    return String.format("<user id=%s >%s</user>", user.getId(), user.getFullName());
  }

  public String toTopicTag(Long topicId) {
    return String.format("<topic id=%s >Đề tài mã số %s</topic>", topicId, topicId);
  }

  public String toTopicTag(TopicTable topic) {
    if (Objects.isNull(topic))
      topic = new TopicTable();
    return this.toTopicTag(topic.getId());
  }

  public String toATag(String href, String innerValue) {
    return String.format("<newTab href=\"%s\" >%s</newTab>", href, innerValue);
  }

  public String toATag(CouncilTable council) {
    var currentSemesterName = ContextAccessor.getBean(SemesterRepository.class)
        .findCurrentSemester().getName();
    var href = String.format("/council/%s/%s", currentSemesterName, council.getId());
    var councilMessage = this.getMessage(MessageCode.Council.CODE, council.getId());
    return this.toATag(href, councilMessage);
  }

  public String requestUserUpdate(String message) {
    var auth = CustomUserDetail.class
        .cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return this.getMessage("user.update", this.toUserTag(auth.getUser()), message);
  }

  public String dashboardTag(String innerValue) {
    return String.format("<newTab href=\"/dashboard\" >%s</newTab>", innerValue);
  }

  public String topicExecuteTag(String innerValue) {
    return String.format("<newTab href=\"/execute\" >%s</newTab>", innerValue);
  }

  public String topicExecuteTag() {
    return this.topicExecuteTag(this.myTopicView);
  }

  public String topicGuideTag() {
    return String.format("<newTab href=\"/guide\" >%s</newTab>", this.myTopicView);
  }

}
