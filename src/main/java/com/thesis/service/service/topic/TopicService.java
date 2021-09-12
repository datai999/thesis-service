package com.thesis.service.service.topic;

import java.util.ArrayList;
import java.util.stream.Collectors;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.AbstractBaseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends AbstractBaseService<TopicTable, TopicRepository> {

  public Object findAll(Sort sort) {
    return super.repository.findAll(sort).parallelStream()
        .map(TopicResponse::new).collect(Collectors.toList());
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
