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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.constant.UserPermission;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.model.topic.TopicGuideTeacherTable;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.util.Strings;
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

  private String password;

  private String phone;

  @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
  private Boolean male;

  @Enumerated(EnumType.STRING)
  private UserPermission permission;

  @OneToOne
  private EducationMethodTable educationMethod;

  @OneToOne
  private MajorTable major;

  @OneToOne
  private SubjectDepartmentTable subjectDepartment;

  @OneToOne
  private DegreeTable degree;

  @OneToMany(targetEntity = TopicStudentTable.class, mappedBy = "student")
  private List<TopicStudentTable> topicExecutes;

  @OneToMany(targetEntity = TopicGuideTeacherTable.class, mappedBy = "guideTeacher")
  private List<TopicGuideTeacherTable> topicGuides;

  @ManyToMany(mappedBy = "reviewTeachers")
  private List<TopicTable> topicReviews;

  @OneToMany(mappedBy = "member")
  private List<CouncilMemberTable> councilMembers;

  public UserTable(Long id) {
    this.setId(id);
  }

  @Override
  public UserTable setId(Long id) {
    super.setId(id);
    return this;
  }

  @JsonIgnore
  public String getFullName() {
    return String.format("%s %s",
        ObjectUtils.defaultIfNull(this.firstName, Strings.EMPTY),
        ObjectUtils.defaultIfNull(this.lastName, Strings.EMPTY));
  }

  @JsonIgnore
  public String getGender() {
    if (Objects.isNull(this.male))
      return null;
    return this.male ? "Nam" : "Nữ";
  }

}
