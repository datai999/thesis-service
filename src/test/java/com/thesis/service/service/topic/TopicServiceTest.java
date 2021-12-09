package com.thesis.service.service.topic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.util.List;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.TopicGuideTeacherTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.repository.topic.TopicGuideTeacherRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.AEntityServiceTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TopicServiceTest
    extends AEntityServiceTest<TopicTable, TopicRepository, TopicService> {

  @BeforeAll
  private void beforeAllTopicService() {
    super.entity
        .setGuideTeachers(List.of(new TopicGuideTeacherTable()))
        .setEducationMethods(List.of(new EducationMethodTable()))
        .setMajors(List.of(new MajorTable()));
    super.service = spy(new TopicService(
        mock(SemesterRepository.class),
        mock(TopicGuideTeacherRepository.class)));
    super.initService();
    when(super.service.mapping()).thenReturn(x -> new Object());
  }

  @Test
  void haveMapping() {
    assertNotNull(service.mapping());
  }

}
