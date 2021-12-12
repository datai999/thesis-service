package com.thesis.service.dto.user.response;

import java.util.Objects;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewTeacherResponse extends UserResponse {

  private long topicReviewSize;

  public ReviewTeacherResponse(UserTable user, Long semesterId) {
    ContextAccessor.getModelConverter().map(user, this);
    this.topicReviewSize = Objects.isNull(semesterId)
        ? user.getTopicReviews().size()
        : user.getTopicReviews().stream()
            .filter(topic -> semesterId.equals(topic.getSemester().getId()))
            .count();
  }

}
