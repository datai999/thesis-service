package com.thesis.service.model.topic;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.person.TeacherTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "tp_topic_assign")
public class TopicAssignTable extends BaseTable {

  @OneToOne(cascade = {CascadeType.PERSIST})
  private TopicTable topic;

  @OneToMany
  private List<TeacherTable> reviewTeachers;

  @OneToOne
  private CouncilTable council;

}