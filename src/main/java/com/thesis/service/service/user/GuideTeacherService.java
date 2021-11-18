package com.thesis.service.service.user;

import java.util.stream.Collectors;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.topic.TopicService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuideTeacherService {

  private final UserRepository userRepository;
  private final TopicService topicService;

  public Object getTopic(long userId, String semesterName) {
    var user = userRepository.findById(userId).orElseThrow();
    var response = user.getTopicGuides().parallelStream()
        .filter(e -> semesterName.equals(e.getSemester().getName())).collect(Collectors.toList());
    return topicService.map(response);
  }

}
