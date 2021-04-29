package com.thesis.service.model.topic;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.thesis.service.model.BaseTable;
import com.thesis.service.model.br.BrConstDataTable;
import com.thesis.service.model.person.PsAcademyStaffTable;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tp_topic")
public class TpTopicTable extends BaseTable {

  private Integer topicCode;

  private String topicName;

  @OneToOne
  @JoinColumn(name = "semester_id")
  private BrConstDataTable semester;

  @OneToOne
  @JoinColumn(name = "major_id")
  private BrConstDataTable major;

  @OneToOne
  @JoinColumn(name = "education_method_id")
  private BrConstDataTable educationMethod;

  @Min(1)
  private Integer minStudentTake;

  @Min(1)
  private Integer maxStudentTake;

  @Type(type = "text")
  private String description;

  @Type(type = "text")
  private String mainTask;

  @Type(type = "text")
  private String thesisTask;

  @Type(type = "text")
  private String note;

  @OneToOne
  @JoinColumn(name = "create_by")
  private PsAcademyStaffTable createBy;

}
