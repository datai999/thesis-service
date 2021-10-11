package com.thesis.service.model.user;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.constant.UserType;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.model.topic.TopicTable;
import org.hibernate.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "us_user")
public class UserTable extends BaseTable {

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
  @Column(nullable = false)
  private UserType type;

  @OneToOne
  private EducationMethodTable educationMethod;

  @OneToOne
  private MajorTable major;

  @OneToOne
  private SubjectDepartmentTable subjectDepartment;

  @OneToOne
  private DegreeTable degree;

  @ManyToMany(mappedBy = "students")
  private List<TopicTable> topicExecutes;

  @ManyToMany(mappedBy = "guideTeachers")
  private List<TopicTable> topicGuides;

  @ManyToMany(mappedBy = "reviewTeachers")
  private List<TopicTable> topicReviews;

  @OneToMany(mappedBy = "member")
  private List<CouncilMemberTable> councilMembers;

  public UserTable setId(Long id) {
    super.setId(id);
    return this;
  }

  public String getFullName() {
    return String.format("%s %s", this.firstName, this.lastName);
  }

  public String getFullCodeName() {
    return String.format("%s - %s", this.getCode(), this.getFullName());
  }

  public String getGender() {
    if (Objects.isNull(this.male))
      return null;
    return this.male ? "Nam" : "Nữ";
  }

}
