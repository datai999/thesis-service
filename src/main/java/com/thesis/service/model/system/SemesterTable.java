package com.thesis.service.model.system;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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

  private Date registerTopicStart;

  private Date registerTopicEnd;

}
