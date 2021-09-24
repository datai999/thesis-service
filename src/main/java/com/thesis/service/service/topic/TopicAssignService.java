package com.thesis.service.service.topic;

import java.util.stream.Collectors;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.topic.TopicAssignRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicAssignService
    extends ABaseService<TopicAssignTable, TopicAssignRepository> {

  @Transactional
  public Object assignReview(TopicTable topic) {
    super.repository.removeAllReview(topic.getId());
    var newAssign = topic.getReviewTeachers().parallelStream().distinct().map(
        reviewTeacher -> new TopicAssignTable().setTopic(topic).setReviewTeacher(reviewTeacher))
        .collect(Collectors.toList());
    super.repository.saveAll(newAssign);
    return true;
  }

}
