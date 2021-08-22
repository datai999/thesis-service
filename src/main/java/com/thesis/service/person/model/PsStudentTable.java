package com.thesis.service.person.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.model.PersonBaseTable;
import com.thesis.service.topic.model.TpTopicAssignTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_student")
public class PsStudentTable extends PersonBaseTable {

  @OneToOne
  private BrConstDataTable major;

  @OneToOne
  private BrConstDataTable educationMethod;

  @ManyToOne
  private TpTopicAssignTable topicAssign;

}
