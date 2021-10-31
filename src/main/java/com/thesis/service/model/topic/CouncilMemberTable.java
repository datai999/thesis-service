package com.thesis.service.model.topic;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.user.UserTable;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tp_council_member")
public class CouncilMemberTable extends BaseTable {

  @ManyToOne
  private CouncilTable council;

  @ManyToOne
  private CouncilRoleTable role;

  @ManyToOne
  private UserTable member;

}
