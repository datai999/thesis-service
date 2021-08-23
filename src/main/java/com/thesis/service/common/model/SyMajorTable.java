package com.thesis.service.common.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.thesis.service.common.dto.MultiLangDto;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "sy_major")
public class SyMajorTable extends BaseTable {

  @Type(type = "json")
  private MultiLangDto name;
}
