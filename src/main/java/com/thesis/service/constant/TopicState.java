package com.thesis.service.constant;

import com.thesis.service.utils.ContextAccessor;

public enum TopicState {
  REGISTER, MID, FINAL, COMPLETE;

  public String getStateName() {
    var messageCode = String.format("topic.state.%s", this.name().toLowerCase());
    return ContextAccessor.getMessageSource().getMessage(messageCode);
  }

}
