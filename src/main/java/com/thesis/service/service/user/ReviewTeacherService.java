package com.thesis.service.service.user;

import java.util.stream.Collectors;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.topic.TopicService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewTeacherService {

  private final TopicRepository topicRepository;
  private final UserRepository userRepository;
  private final TopicService topicService;

  public Object getTopicReview(long subjectDepartmentId, String semesterName) {

    var topicExample = new TopicTable();
    topicExample.setSubjectDepartment(new SubjectDepartmentTable(subjectDepartmentId));
    topicExample.setSemester(new SemesterTable(semesterName));

    var queryResult = topicRepository.findAll(Example.of(topicExample));

    queryResult = queryResult.parallelStream()
        .filter(e -> {
          if (CollectionUtils.isEmpty(e.getStudents()))
            return false;
          if (e.getStudents().stream().allMatch(student -> !student.getMidPass()))
            return false;
          return true;
        })
        .collect(Collectors.toList());

    return topicService.map(queryResult);
  }

  public Object getTopic(long userId, String semesterName) {
    var user = userRepository.findById(userId).orElseThrow();
    var response = user.getTopicReviews().parallelStream()
        .filter(e -> semesterName.equals(e.getSemester().getName())).collect(Collectors.toList());
    return topicService.map(response);
  }

}
