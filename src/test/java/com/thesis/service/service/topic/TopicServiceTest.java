package com.thesis.service.service.topic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.function.Consumer;
import com.thesis.service.constant.TopicState;
import com.thesis.service.model.score.ScoreTable;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.TopicGuideTeacherTable;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.score.ScoreRepository;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.repository.topic.TopicGuideTeacherRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.utils.ContextAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.test.util.ReflectionTestUtils;

public class TopicServiceTest
    extends AEntityServiceTest<TopicTable, TopicRepository, TopicService> {

  private ScoreRepository scoreRepository = mock(ScoreRepository.class);

  @Override
  protected TopicService spyService() {
    var service = spy(new TopicService(
        mock(SemesterRepository.class),
        mock(TopicGuideTeacherRepository.class),
        scoreRepository));
    super.contextAccessor.when(() -> ContextAccessor.getBean(TopicService.class))
        .thenReturn(service);
    return service;
  }

  @Override
  protected Consumer<TopicTable> extendEntity() {
    return (TopicTable entity) -> entity
        .setGuideTeachers(List.of(new TopicGuideTeacherTable().setGuideTeacher(new UserTable())))
        .setEducationMethods(List.of(new EducationMethodTable()))
        .setMajors(List.of(new MajorTable()));
  }

  @BeforeEach
  void beforeEach() {
    this.scoreRepository = mock(ScoreRepository.class);
    ReflectionTestUtils.setField(this.service, "scoreRepository", this.scoreRepository);
  }

  @Test
  void haveMapping() {
    assertNotNull(service.mapping());
  }

  @Test
  void getState_haveStudent() {
    var input = super.entitySupplier.get().setStudents(List.of(new TopicStudentTable()));
    var actual = service.getState(input);
    assertEquals(TopicState.MID, actual);
  }

  @Test
  void getState_allStudentFalse() {
    var input = super.entitySupplier.get()
        .setStudents(List.of(new TopicStudentTable().setMidPass(false)));
    var actual = service.getState(input);
    assertEquals(TopicState.COMPLETE, actual);
  }

  @Test
  void getState_allStudentPassMidNotHaveScore() {
    var input = super.entitySupplier.get()
        .setStudents(List.of(new TopicStudentTable().setMidPass(true)));
    var actual = service.getState(input);
    assertEquals(TopicState.FINAL, actual);
  }

  @Test
  void getState_allStudentPassMidHaveScore() {
    var student = UserTable.builder().id(0L).build();
    var input = super.entitySupplier.get()
        .setStudents(List.of(new TopicStudentTable().setMidPass(true).setStudent(student)));
    when(scoreRepository.findAll(any(Example.class)))
        .thenReturn(List.of(new ScoreTable().setStudent(student)));
    var actual = service.getState(input);
    assertEquals(TopicState.COMPLETE, actual);
  }

}
