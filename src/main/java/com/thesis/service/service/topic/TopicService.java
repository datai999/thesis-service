package com.thesis.service.service.topic;

import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.person.StudentRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends AbstractBaseService<TopicTable, TopicRepository> {

  private final StudentRepository studentRepository;

  public Object studentRegister(Long topicId) {
    var topic = super.repository.findById(topicId).orElseThrow();
    // TOOD: get current user login from token
    Long studentId = 1L;
    var student = studentRepository.findById(studentId).orElseThrow();

    // var studentExecuteTopic =
    // ObjectUtils.defaultIfNull(topic.getStudents(), new ArrayList<StudentTable>());
    // studentExecuteTopic.add(student);

    // return super.repository.save(topic.setStudents(studentExecuteTopic));
    return null;
  }

}
