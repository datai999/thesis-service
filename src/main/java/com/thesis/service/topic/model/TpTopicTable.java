package com.thesis.service.topic.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.dto.MultiLangDto;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.person.model.PsAcademyStaffTable;

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

  @NotNull
  private Integer topicCode;

  @Type(type = "json")
  private MultiLangDto topicName;

  @OneToOne
  @JoinColumn(name = "semester_id")
  private BrConstDataTable semester;

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "major_id", columnDefinition = "bigint[]")
  private List<Long> majorId;

  @Transient
  private List<BrConstDataTable> major;

  @OneToOne
  @JoinColumn(name = "education_method_id")
  private BrConstDataTable educationMethod;

  @Min(1)
  private Integer minStudentTake;

  @Min(1)
  private Integer maxStudentTake;

  @Type(type = "json")
  private MultiLangDto description;

  @Type(type = "json")
  private MultiLangDto topicTask;

  @Type(type = "json")
  private MultiLangDto thesisTask;

  @Type(type = "text")
  private String note;

  @OneToOne
  @JoinColumn(name = "create_by")
  private PsAcademyStaffTable createBy;

  @Override
  public String getTableName() {
    return "tp_topic";
  }

}
