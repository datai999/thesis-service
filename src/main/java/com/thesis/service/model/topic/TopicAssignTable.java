package com.thesis.service.model.topic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.model.user.UserTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tp_topic_assign")
public class TopicAssignTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private TopicTable topic;

  @ManyToOne
  private UserTable student;

  @ManyToOne
  private UserTable guideTeacher;

  @ManyToOne
  private UserTable reviewTeacher;

}
