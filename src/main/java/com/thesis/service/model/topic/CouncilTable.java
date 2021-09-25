package com.thesis.service.model.topic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.user.UserTable;
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

  @OneToOne
  private SubjectDepartmentTable subjectDepartment;

  private String reserveRoom;

  private LocalDate reserveDate;

  private LocalTime startTime;

  private LocalTime endTime;

  @ManyToMany
  @JoinTable(name = "tp_council_member",
      joinColumns = @JoinColumn(name = "council_id"),
      inverseJoinColumns = @JoinColumn(name = "member_id"))
  private List<UserTable> members;

  @Type(type = "text")
  private String note;

}
