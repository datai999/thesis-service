package com.thesis.service.model.br;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.vladmihalcea.hibernate.type.array.LongArrayType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "long-array", typeClass = LongArrayType.class)
public abstract class BrBaseTable implements Serializable {

  @Id
  @GeneratedValue
  private long id;

  private boolean deleted = false;

  @Column(updatable = false)
  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;

}
