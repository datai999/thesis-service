package com.thesis.service.model.topic;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tp_council")
public class CouncilTable extends BaseTable {

  // @OneToOne
  // @JoinColumn(name = "subject_department_id")
  // private ConstDataTable subjectDepartment;

  // private String reserveRoom;

  // private LocalDate reserveDate;

  // private LocalTime startTime;

  // private LocalTime endTime;

  // @JsonIgnore
  // @Type(type = "list-array")
  // @Column(name = "role_id", columnDefinition = "bigint[]")
  // private Collection<Long> roleId;

  // @Transient
  // private List<ConstDataTable> role;

  // @JsonIgnore
  // @Type(type = "list-array")
  // @Column(name = "teacher_code", columnDefinition = "varchar[]")
  // private Collection<String> teacherCode;

  // @Transient
  // private List<TeacherTable> teacher;

}
