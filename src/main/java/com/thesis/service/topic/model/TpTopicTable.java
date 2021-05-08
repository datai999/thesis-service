package com.thesis.service.topic.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.person.model.PsAcademyStaffTable;

import org.hibernate.annotations.Type;
import org.springframework.util.CollectionUtils;

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

  @PrePersist
  private void prePersist() {
    if (!CollectionUtils.isEmpty(this.major))
      this.majorId = this.major.stream().map(BrConstDataTable::getId).collect(Collectors.toList());
  }
}
