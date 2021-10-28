package com.thesis.service.model.user;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.thesis.service.constant.UserPermission;
import com.thesis.service.constant.UserType;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.model.topic.TopicTable;
import org.apache.commons.collections4.CollectionUtils;
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
  @Column(name = "permissions", columnDefinition = "text[]")
  private List<String> permissionValues;

  @Transient
  private List<UserPermission> permissions;

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

  @PostLoad
  private void fillTransient() {
    if (CollectionUtils.isNotEmpty(this.permissionValues)) {
      this.permissions = this.permissionValues.parallelStream()
          .map(UserPermission::of).collect(Collectors.toList());
    }
  }

  @PrePersist
  private void fillPersistent() {
    if (CollectionUtils.isNotEmpty(this.permissions)) {
      this.permissionValues = this.permissions.parallelStream()
          .map(UserPermission::getValue).collect(Collectors.toList());
    }
  }

  @Override
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
