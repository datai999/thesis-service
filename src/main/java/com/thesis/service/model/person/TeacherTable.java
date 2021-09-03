package com.thesis.service.model.person;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.model.topic.TopicTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_teacher")
public class TeacherTable extends PersonBaseTable {

  @OneToOne
  private SubjectDepartmentTable subjectDepartment;

  @OneToOne
  private DegreeTable degree;

  @ManyToMany
  private List<TopicTable> topics;

  @ManyToOne
  private TopicAssignTable topicAssign;

}
