package com.thesis.service.model.topic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import org.hibernate.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tp_council")
public class CouncilTable extends BaseTable {

  @ManyToOne(optional = false)
  private SemesterTable semester;

  @OneToOne(optional = false)
  private SubjectDepartmentTable subjectDepartment;

  private String location;

  private LocalDate reserveDate;

  private LocalTime startTime;

  private LocalTime endTime;

  @OneToMany(mappedBy = "council")
  private List<CouncilMemberTable> members;

  @Type(type = "text")
  private String note;

}
