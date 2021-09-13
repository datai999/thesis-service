package com.thesis.service.service.topic;

import java.util.ArrayList;
import java.util.function.Function;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends ABaseService<TopicTable, TopicRepository> {

  @Override
  protected Function<TopicTable, ?> mapping() {
    return TopicResponse::new;
  }

  public Object studentRegister(Long topicId) {
    var topic = super.repository.findById(topicId).orElseThrow();

    var studentExecuteTopic =
        ObjectUtils.defaultIfNull(topic.getStudents(), new ArrayList<UserTable>());
    studentExecuteTopic.add(super.getAuth());

    var topicResponse = super.repository.save(topic.setStudents(studentExecuteTopic));

    return new TopicResponse(topicResponse);
  }

  public Object findByUserAndRole(Long userId, TopicRole role, Sort sort) {

    if (TopicRole.STUDENT.equals(role)) {
      return null;
    }

    if (TopicRole.GUIDE_TEACHER.equals(role)) {
      return null;
    }

    return null;
  }

}
