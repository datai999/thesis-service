package com.thesis.service.model.user;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.constant.UserType;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.topic.TopicTable;
import org.hibernate.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "us_user")
public class UserTable extends BaseTable {

  @Column(unique = true)
  private String code;

  private String firstName;

  private String lastName;

  @Column(unique = true)
  private String email;

  private String phone;

  @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
  private Boolean male;

  @Type(type = "list-array")
  @Column(columnDefinition = "text[]")
  private List<String> roles;

  @Enumerated(EnumType.STRING)
  private UserType type;

  @OneToOne
  private EducationMethodTable educationMethod;

  @OneToOne
  private MajorTable major;

  @OneToOne
  private SubjectDepartmentTable subjectDepartment;

  @OneToOne
  private DegreeTable degree;

  @ManyToMany
  @JoinTable(name = "tp_topic_assign",
      joinColumns = @JoinColumn(name = "guide_teacher_id"),
      inverseJoinColumns = @JoinColumn(name = "topic_id"))
  private List<TopicTable> topicExecutes;

  @ManyToMany
  @JoinTable(name = "tp_topic_assign",
      joinColumns = @JoinColumn(name = "guide_teacher_id"),
      inverseJoinColumns = @JoinColumn(name = "topic_id"))
  private List<TopicTable> topicGuides;

  @ManyToMany
  @JoinTable(name = "tp_topic_assign",
      joinColumns = @JoinColumn(name = "review_teacher_id"),
      inverseJoinColumns = @JoinColumn(name = "topic_id"))
  private List<TopicTable> topicReviews;

  public String getFullName() {
    return String.format("%s %s", this.firstName, this.lastName);
  }

  public String getFullCodeName() {
    return String.format("%s - %s", this.getCode(), this.getFullName());
  }

}
