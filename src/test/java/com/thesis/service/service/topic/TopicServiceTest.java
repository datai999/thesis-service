package com.thesis.service.service.topic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import java.util.List;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.TopicGuideTeacherTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.repository.topic.TopicGuideTeacherRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.AEntityServiceTest;
import org.junit.jupiter.api.Test;

public class TopicServiceTest
    extends AEntityServiceTest<TopicTable, TopicRepository, TopicService> {

  @Override
  protected TopicService spyService() {
    super.entity
        .setGuideTeachers(List.of(new TopicGuideTeacherTable().setGuideTeacher(new UserTable())))
        .setEducationMethods(List.of(new EducationMethodTable()))
        .setMajors(List.of(new MajorTable()));
    return spy(new TopicService(
        mock(SemesterRepository.class),
        mock(TopicGuideTeacherRepository.class),
        mock(ScoreRepository.class)));
  }

  @Test
  void haveMapping() {
    assertNotNull(service.mapping());
  }

}
