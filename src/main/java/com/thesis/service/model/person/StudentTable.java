package com.thesis.service.model.person;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.TopicTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ps_student")
public class StudentTable extends PersonBaseTable {

  @OneToOne
  private EducationMethodTable educationMethod;

  @OneToOne
  private MajorTable major;

  @ManyToMany
  private List<TopicTable> topics;

}
