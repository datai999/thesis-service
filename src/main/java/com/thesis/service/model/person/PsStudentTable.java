package com.thesis.service.model.person;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.common.model.SyEducationMethodTable;
import com.thesis.service.common.model.SyMajorTable;
import com.thesis.service.model.topic.TpTopicTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_student")
public class PsStudentTable extends PersonBaseTable {

  @OneToOne
  private SyEducationMethodTable educationMethod;

  @OneToOne
  private SyMajorTable major;

  @ManyToMany
  private List<TpTopicTable> topics;

}
