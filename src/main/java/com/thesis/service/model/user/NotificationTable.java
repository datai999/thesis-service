package com.thesis.service.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "us_notification")
public class NotificationTable extends BaseTable {

  @ManyToOne
  private UserTable receiver;

  private String message;

  @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
  private Boolean seen;

}
