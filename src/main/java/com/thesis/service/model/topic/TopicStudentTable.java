package com.thesis.service.model.topic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.user.UserTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Entity
@DynamicUpdate
@Table(name = "tp_student")
public class TopicStudentTable extends BaseTable {

  @ManyToOne
  private TopicTable topic;

  @ManyToOne
  private UserTable student;

  @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
  private Boolean midPass;

}
