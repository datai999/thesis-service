package com.thesis.service.model.system;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sy_semester_property")
public class SemesterPropertyTable extends BaseTable {

  @Builder.Default
  private Boolean defaultMid = true;

  private LocalDateTime createTopicStart;
  private LocalDateTime createTopicEnd;

  private LocalDateTime registerTopicStart;
  private LocalDateTime registerTopicEnd;

  private LocalDateTime executeTopicStart;
  private LocalDateTime executeTopicEnd;

  private LocalDateTime midMarkStart;
  private LocalDateTime midMarkEnd;

}
