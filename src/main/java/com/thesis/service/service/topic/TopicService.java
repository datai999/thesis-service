package com.thesis.service.service.topic;

import java.util.ArrayList;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.AbstractBaseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends AbstractBaseService<TopicTable, TopicRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return TopicResponse.class;
  }

  public Object studentRegister(Long topicId) {
    var topic = super.repository.findById(topicId).orElseThrow();

    var studentExecuteTopic =
        ObjectUtils.defaultIfNull(topic.getStudents(), new ArrayList<UserTable>());
    studentExecuteTopic.add(super.getAuth());

    var topicResponse = super.repository.save(topic.setStudents(studentExecuteTopic));

    return new TopicResponse(topicResponse);
  }

}
