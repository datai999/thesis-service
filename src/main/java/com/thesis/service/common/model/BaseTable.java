package com.thesis.service.common.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.IService;
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
  public Object mapIdOrCode() {

    var fields = List.of(this.getClass().getDeclaredFields());

    var fieldNames = fields.parallelStream().map(Field::getName).collect(Collectors.toList());

    fields.parallelStream().forEach(field -> {

      var identify = "Id";

      if (!fieldNames.contains(field.getName().concat(identify))) {
        if (!fieldNames.contains(field.getName().concat("Code"))) {
          return;
        }
        identify = "Code";
      }

      try {
        var targetField = this.getClass().getDeclaredField(field.getName().concat(identify));

        field.setAccessible(true);
        targetField.setAccessible(true);

        var value = field.get(this);

        if (Iterable.class.isAssignableFrom(field.getType())) {
          Collection<BaseTable> valueList = Collection.class.cast(value);
          if (!CollectionUtils.isEmpty(valueList)) {
            targetField.set(this, valueList.stream().map(baseValue -> {
              if (Objects.isNull(baseValue))
                return null;
              return baseValue.getId();
            }).collect(Collectors.toList()));
          }
        } else {
          targetField.set(this, BaseTable.class.cast(value).getId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    return this;
  }

  @SuppressWarnings("unchecked")
  public <E extends BaseTable, S extends BaseRepository<E> & IService<E>> void setById(S service, String... fields) {

    List.of(fields).parallelStream().forEach(field -> {
      try {
        var sourceField = this.getClass().getDeclaredField(field.concat("Id"));
        var targetField = this.getClass().getDeclaredField(field);

        sourceField.setAccessible(true);
        targetField.setAccessible(true);

        var id = sourceField.get(this);

        if (Iterable.class.isAssignableFrom(sourceField.getType())) {

          var ids = Collection.class.cast(id);

          if (!CollectionUtils.isEmpty(ids)) {
            var values = service.findAllById(ids);
            if (Set.class.isAssignableFrom(targetField.getType()))
              targetField.set(this, values.stream().collect(Collectors.toSet()));
            else
              targetField.set(this, values);
          }
        } else {
          targetField.set(this, service.findById(Long.valueOf(id.toString())));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
