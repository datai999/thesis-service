package com.thesis.service.topic.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.dto.MultiLangDto;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.model.SyMajorTable;
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

  @JsonIgnore
  @Type(type = "list-array")
  @Column(name = "major_id", columnDefinition = "bigint[]")
  private List<Long> majorId;

  @Transient
  private List<SyMajorTable> major;

  @OneToOne
  private BrConstDataTable educationMethod;

  @Min(1)
  private Integer minStudentTake = 1;

  @Min(1)
  @Max(3)
  private Integer maxStudentTake = 3;

  @Type(type = "json")
  private MultiLangDto description;

  @Type(type = "json")
  private MultiLangDto task;

  @Type(type = "text")
  private String documentReference;

}
