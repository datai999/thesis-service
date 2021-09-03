package com.thesis.service.person.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.common.model.PersonBaseTable;
import com.thesis.service.common.model.SyDegreeTable;
import com.thesis.service.common.model.SySubjectDepartmentTable;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.model.TpTopicTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_teacher")
public class PsTeacherTable extends PersonBaseTable {

  @OneToOne
  private SySubjectDepartmentTable subjectDepartment;

  @OneToOne
  private SyDegreeTable degree;

  @ManyToMany
  private List<TpTopicTable> topics;

  @ManyToOne
  private TpTopicAssignTable topicAssign;

}
