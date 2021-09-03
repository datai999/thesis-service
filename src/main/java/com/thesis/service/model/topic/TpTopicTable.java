package com.thesis.service.model.topic;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.thesis.service.dto.MultiLangDto;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.person.PsStudentTable;
import com.thesis.service.model.person.PsTeacherTable;
import com.thesis.service.model.system.SyEducationMethodTable;
import com.thesis.service.model.system.SyMajorTable;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "tp_topic")
public class TpTopicTable extends BaseTable {

  @Type(type = "json")
  private MultiLangDto name;

  private Integer semester;

  private Boolean thesis = false;

  @ManyToMany
  private List<SyEducationMethodTable> educationMethods;

  @ManyToMany
  private List<SyMajorTable> majors;

  @Min(1)
  private Integer minStudentTake = 1;

  @Min(1)
  @Max(3)
  private Integer maxStudentTake = 3;

  @Type(type = "text")
  private String description;

  @Type(type = "text")
  private String task;

  @Type(type = "text")
  private String documentReference;

  @ManyToMany
  private List<PsTeacherTable> guideTeachers;

  @ManyToMany
  private List<PsStudentTable> students;

}
