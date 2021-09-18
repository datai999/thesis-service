package com.thesis.service.model.system;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import com.thesis.service.constant.SemesterStatus;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sy_semester")
public class SemesterTable extends BaseTable {

  @Column(nullable = false)
  private String name;

  private LocalDateTime registerTopicStart;

  private LocalDateTime registerTopicEnd;

  @Enumerated(EnumType.STRING)
  private SemesterStatus status;

}
