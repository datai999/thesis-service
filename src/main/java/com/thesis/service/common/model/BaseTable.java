package com.thesis.service.common.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.IService;
import com.thesis.service.person.service.IPersonService;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.array.LongArrayType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.TextType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
  protected boolean deleted;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  protected Date createdAt;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  protected Date updatedAt;

  @JsonIgnore
  public String getTableName() {
    return null;
  }

  @SuppressWarnings("unchecked")
  public Object mapId() {

    var fields = List.of(this.getClass().getDeclaredFields());

    var fieldNames = fields.parallelStream().map(Field::getName).collect(Collectors.toList());

    fields.parallelStream().forEach(field -> {

      var identify = "Code";

      if (!fieldNames.contains(field.getName().concat(identify))) {
        if (!fieldNames.contains(field.getName().concat("Id"))) {
          return;
        }
        identify = "Id";
      }

      try {
        var targetField = this.getClass().getDeclaredField(field.getName().concat(identify));

        field.setAccessible(true);
        targetField.setAccessible(true);

        var value = field.get(this);

        if (Objects.isNull(value))
          return;

        if (Iterable.class.isAssignableFrom(field.getType())) {

          Collection<?> idList;

          if ("Code".equals(identify)) {
            Collection<PersonBaseTable> valueList = Collection.class.cast(value);
            idList = valueList.stream().map(item -> {
              return Objects.isNull(item) ? null : item.getCode();
            }).collect(Collectors.toList());
          } else {
            Collection<BaseTable> valueList = Collection.class.cast(value);
            idList = valueList.stream().map(item -> {
              return Objects.isNull(item) ? null : item.getId();
            }).collect(Collectors.toList());
          }

          if (!CollectionUtils.isEmpty(idList)) {
            targetField.set(this, idList);
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
  public <E extends BaseTable, S extends BaseRepository<E> & IService<E>> void setById(S service,
      String... fields) {

    List.of(fields).parallelStream().forEach(field -> {
      var identify = "Code";
      try {
        Field sourceField;
        try {
          sourceField = this.getClass().getDeclaredField(field.concat(identify));
        } catch (NoSuchFieldException e) {
          identify = "Id";
          sourceField = this.getClass().getDeclaredField(field.concat(identify));
        }
        var targetField = this.getClass().getDeclaredField(field);

        sourceField.setAccessible(true);
        targetField.setAccessible(true);

        var id = sourceField.get(this);
        if (Objects.isNull(id))
          return;

        if (Iterable.class.isAssignableFrom(sourceField.getType())) {

          List<?> idOriginList = new ArrayList<>((Collection<?>) id);
          List<?> ids = idOriginList.parallelStream().distinct().collect(Collectors.toList());
          List<E> response;

          if (ids.size() <= 1 && Objects.isNull(ids.get(0))) {
            response = Collections.nCopies(idOriginList.size(), null);
          }

          else {

            if ("Code".equals(identify)) {
              var personService = IPersonService.class.cast(service);
              response = personService
                  .findAllByCode(
                      ids.parallelStream().map(String::valueOf).collect(Collectors.toList()));
            } else {
              response = service
                  .findAllById(ids.parallelStream().map(x -> Long.valueOf(x.toString()))
                      .collect(Collectors.toList()));
            }
          }

          if (Set.class.isAssignableFrom(targetField.getType()))
            targetField.set(this, response.stream().collect(Collectors.toSet()));
          else
            targetField.set(this, response);

        } else {
          Object value;
          if ("Code".equals(identify)) {
            var personService = IPersonService.class.cast(service);
            value = personService.findAllByCode(List.of(id.toString())).get(0);
          } else {
            value = service.findById(Long.valueOf(id.toString()));
          }
          targetField.set(this, value);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
