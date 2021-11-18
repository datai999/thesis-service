package com.thesis.service.model.topic;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.thesis.service.dto.MultiLangDto;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.user.UserTable;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "tp_topic")
public class TopicTable extends BaseTable {

  @Type(type = "json")
  @Column(columnDefinition = "TEXT")
  private MultiLangDto name;

  @ManyToOne(optional = false)
  private SemesterTable semester;

  @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
  private Boolean thesis;

  @ManyToMany
  private List<EducationMethodTable> educationMethods;

  @ManyToMany
  private List<MajorTable> majors;

  @ManyToOne
  private SubjectDepartmentTable subjectDepartment;

  @Min(1)
  @Column(columnDefinition = "INTEGER DEFAULT 1")
  private Integer minStudentTake;

  @Min(1)
  @Max(3)
  @Column(columnDefinition = "INTEGER DEFAULT 3")
  private Integer maxStudentTake;

  @Type(type = "text")
  private String description;

  @Type(type = "text")
  private String task;

  @Type(type = "text")
  private String documentReference;

  @ManyToMany
  @JoinTable(name = "tp_topic_assign",
      joinColumns = @JoinColumn(name = "topic_id"),
      inverseJoinColumns = @JoinColumn(name = "student_id"))
  private List<UserTable> students;

  @ManyToMany
  @JoinTable(name = "tp_topic_assign",
      joinColumns = @JoinColumn(name = "topic_id"),
      inverseJoinColumns = @JoinColumn(name = "guide_teacher_id"))
  private List<UserTable> guideTeachers;

  @ManyToMany
  @JoinTable(name = "tp_topic_assign",
      joinColumns = @JoinColumn(name = "topic_id"),
      inverseJoinColumns = @JoinColumn(name = "review_teacher_id"))
  private List<UserTable> reviewTeachers;

  @ManyToOne
  private CouncilTable council;

  public String getMultiName(String format) {
    return Objects.isNull(this.name) ? null
        : String.format(format, this.name.getVi(), this.name.getEn());
  }

  public String getType() {
    if (Objects.isNull(this.thesis))
      return null;
    return this.thesis ? "Luận văn" : "Đề cương";
  }

}
