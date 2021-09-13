package com.thesis.service.dto.user.response;

import java.util.List;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import lombok.Data;

@Data
public class TopicOfUserResponse {

  private List<TopicResponse> execute;
  private List<TopicResponse> guide;
  private List<TopicResponse> review;

}
