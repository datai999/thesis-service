package com.thesis.service.service;

import com.thesis.service.config.firebase.FirebaseAuthenticationToken;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.topic.TopicTable;
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

  private final MessageSource messageSource;

  public String getMessage(String code, Object... args) {
    return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
  }

  public String requestUserUpdate(String message) {
    var auth = FirebaseAuthenticationToken.class
        .cast(SecurityContextHolder.getContext().getAuthentication());
    var requestUserATag = this.toATag("/#", auth.getPrincipal().getFullName());
    return this.getMessage("user.update", requestUserATag, message);
  }

  public String toATag(String href, String innerValue) {
    return String.format("<a target=\"_blank\" href=\"%s%s\">%s</a>", HOST, href, innerValue);
  }

  public String toATagStudent(TopicTable topic) {
    return this.getMessage("topic.students",
        this.toATag("/my/topics/execute", topic.getMultiName("[%s,%s]")));
  }

  public String toATagGuideTeacher(TopicTable topic) {
    return this.getMessage("topic.guideTeachers",
        this.toATag("/my/topics/guide", topic.getMultiName("[%s,%s]")));
  }

  public String toATagReviewTeacher(TopicTable topic) {
    return this.getMessage("topic.reviewTeachers",
        this.toATag("/my/topics/review", topic.getMultiName("[%s,%s]")));
  }

  public String toATag(CouncilTable council) {
    var href = String.format("/councils/%s", council.getSubjectDepartment().getId());
    var councilMessage = this.getMessage("council", council.getId());
    return this.toATag(href, councilMessage);
  }

}
