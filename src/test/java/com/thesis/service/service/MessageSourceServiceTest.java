package com.thesis.service.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.thesis.service.model.topic.TopicTable;
import org.junit.jupiter.api.Test;

public class MessageSourceServiceTest extends ABaseServiceTest {

  @Test
  void toUserTag_null() {
    var actual = super.messageSource.toUserTag(null);
    assertNotNull(actual);
  }

  @Test
  void toTopicTag_null() {
    TopicTable input = null;
    var actual = super.messageSource.toTopicTag(input);
    assertNotNull(actual);
  }

  @Test
  void dashboardTag() {
    var actual = super.messageSource.dashboardTag("");
    assertNotNull(actual);
  }

  @Test
  void topicExecuteTag_withParam() {
    var actual = super.messageSource.topicExecuteTag("");
    assertNotNull(actual);
  }

  @Test
  void topicExecuteTag() {
    var actual = super.messageSource.topicExecuteTag();
    assertNotNull(actual);
  }

  @Test
  void topicGuideTag() {
    var actual = super.messageSource.topicGuideTag();
    assertNotNull(actual);
  }

}
