package com.thesis.service.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.array.LongArrayType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.TextType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "int-array", typeClass = IntArrayType.class)
@TypeDef(name = "long-array", typeClass = LongArrayType.class)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@TypeDef(name = "text", typeClass = TextType.class)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public abstract class BaseTable implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
  protected boolean deleted;

  @CreationTimestamp
  @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
  protected LocalDateTime createdAt = LocalDateTime.now();

  @UpdateTimestamp
  @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime updatedAt = LocalDateTime.now();

}
