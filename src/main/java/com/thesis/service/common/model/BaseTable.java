package com.thesis.service.common.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
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
import org.springframework.util.CollectionUtils;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "int-array", typeClass = IntArrayType.class)
@TypeDef(name = "long-array", typeClass = LongArrayType.class)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@TypeDef(name = "text", typeClass = TextType.class)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public abstract class BaseTable implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  private Boolean deleted = false;

  @Column(updatable = false)
  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;

  @SuppressWarnings("unchecked")
  public Object mapId() {

    var fields = List.of(this.getClass().getDeclaredFields());

    var fieldNames = fields.parallelStream().map(Field::getName).collect(Collectors.toList());

    fields.parallelStream().forEach(field -> {

      if (!fieldNames.contains(field.getName().concat("Id")))
        return;

      try {
        var toField = this.getClass().getDeclaredField(field.getName().concat("Id"));

        field.setAccessible(true);
        toField.setAccessible(true);

        var value = field.get(this);

        if (Iterable.class.isAssignableFrom(field.getType())) {
          List<BaseTable> valueList = List.class.cast(value);
          if (!CollectionUtils.isEmpty(valueList)) {
            toField.set(this, valueList.stream().map(BaseTable::getId).collect(Collectors.toList()));
          }
        } else {
          toField.set(this, BaseTable.class.cast(value).getId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    return this;
  }

}
