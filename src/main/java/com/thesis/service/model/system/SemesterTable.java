package com.thesis.service.model.system;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import com.thesis.service.constant.SemesterStatus;
import com.thesis.service.model.BaseTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.utils.ContextAccessor;
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

  @Enumerated(EnumType.STRING)
  private SemesterStatus status;

  private LocalDateTime registerTopicStart;

  private LocalDateTime registerTopicEnd;

  private LocalDateTime topicStart;

  private LocalDateTime topicEnd;

  private LocalDateTime thesisStart;

  private LocalDateTime thesisEnd;

  public SemesterTable(String name) {
    var semester = ContextAccessor.getBean(SemesterRepository.class)
        .findTopByName(name).orElseThrow();
    ContextAccessor.getModelConverter().map(semester, this);
  }

  public boolean isCurrent() {
    return SemesterStatus.USING.equals(this.status);
  }

}
