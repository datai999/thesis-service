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
import com.thesis.service.repository.topic.TopicStudentRepository;
import com.thesis.service.service.AEntityServiceTest;
import com.thesis.service.service.system.SemesterService;
import com.thesis.service.service.system.testsuite.SemesterServiceTS;
import com.thesis.service.service.system.testsuite.SubjectDepartmentServiceTS;
import com.thesis.service.utils.ContextAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

public class TopicServiceTest
    extends AEntityServiceTest<TopicTable, TopicRepository, TopicService> {

  private SemesterRepository semesterRepository;
  private ScoreRepository scoreRepository;

  @Override
  protected TopicService spyService() {
    var service = spy(new TopicService(
        mock(SemesterRepository.class),
        mock(TopicGuideTeacherRepository.class),
        mock(TopicStudentRepository.class),
        mock(ScoreRepository.class),
        mock(SemesterService.class)));
    super.contextAccessor.when(() -> ContextAccessor.getBean(TopicService.class))
        .thenReturn(service);
    return service;
  }

  @Override
  protected Consumer<TopicTable> extendEntity() {
    return (TopicTable entity) -> entity
        .setGuideTeachers(
            List.of(new TopicGuideTeacherTable().setMain(false).setGuideTeacher(new UserTable())))
        .setEducationMethods(List.of(new EducationMethodTable()))
        .setMajors(List.of(new MajorTable()));
  }

  @BeforeEach
  void beforeEach() {
    this.semesterRepository = mock(SemesterRepository.class);
    this.scoreRepository = mock(ScoreRepository.class);
    ReflectionTestUtils.setField(this.service, "semesterRepository", this.semesterRepository);
    ReflectionTestUtils.setField(this.service, "scoreRepository", this.scoreRepository);
  }

  @Test
  void haveMapping() {
    assertNotNull(service.mapping());
  }

  @Test
  void create_guideTeacherEmpty() {
    when(semesterRepository.findCurrentSemester())
        .thenReturn(SemesterServiceTS.CURRENT_SEMESTER.get());
    var input = super.entity.setGuideTeachers(List.of());
    var actual = service.create(input);
    assertNotNull(actual);
  }

  @Test
  void update_guideTeacherEmpty() {
    when(semesterRepository.findCurrentSemester())
        .thenReturn(SemesterServiceTS.CURRENT_SEMESTER.get());
    var input = super.entity.setGuideTeachers(List.of());
    var actual = service.update(input);
    assertNotNull(actual);
  }

  @Test
  void findByExample_educationMethodEmpty() {
    when(super.repository.findAll(Mockito.<Example<TopicTable>>any(), any(Sort.class)))
        .thenReturn(List.of(super.entity.setEducationMethods(List.of())));
    var actual = super.service.findByExample(super.entity, Sort.unsorted());
    assertNotNull(actual);
  }

  @Test
  void findByExample_majorEmpty() {
    when(super.repository.findAll(Mockito.<Example<TopicTable>>any(), any(Sort.class)))
        .thenReturn(List.of(super.entity.setMajors(List.of())));
    var actual = super.service.findByExample(super.entity, Sort.unsorted());
    assertNotNull(actual);
  }

  @Test
  void findNeedAssignCouncil_() {
    var actual =
        super.service.findNeedAssignCouncil(SubjectDepartmentServiceTS.VALID_ID, Sort.unsorted());
    assertNotNull(actual);
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
    when(scoreRepository.findAll(Mockito.<Example<ScoreTable>>any()))
        .thenReturn(List.of(new ScoreTable().setStudent(student)));
    var actual = service.getState(input);
    assertEquals(TopicState.COMPLETE, actual);
  }

}
