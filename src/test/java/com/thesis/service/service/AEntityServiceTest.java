package com.thesis.service.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import com.thesis.service.model.BaseTable;
import com.thesis.service.repository.BaseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.util.ReflectionTestUtils;

public abstract class AEntityServiceTest<T extends BaseTable, R extends BaseRepository<T>, S extends ABaseService<T, R>>
    extends ABaseServiceTest {

  private static final int REPEATED_TEST = 1;

  protected R repository;
  protected S service;
  protected T entity;

  @BeforeAll
  @SuppressWarnings("unchecked")
  private void beforeAllService() {
    var entityClass = (Class<T>) this.getGenericType(0);
    try {
      this.entity = entityClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.entity.setId(0L);
    this.repository = mock((Class<R>) this.getGenericType(1));
    when(repository.save(any(entityClass))).thenAnswer(i -> i.getArgument(0));

    this.service = spyService();
    ReflectionTestUtils.setField(this.service, "repository", this.repository);
    ReflectionTestUtils.setField(this.service, "mapper", super.mapper);
    ReflectionTestUtils.setField(this.service, "messageSource", super.messageSource);
  }

  protected abstract S spyService();

  private Type getGenericType(int index) {
    return ParameterizedType.class
        .cast(this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[index];
  }

  @Test
  @RepeatedTest(REPEATED_TEST)
  void findById() {
    when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
    assertNotNull(service.findById(entity.getId()));
  }

  @Test
  @RepeatedTest(REPEATED_TEST)
  void findByExample() {
    Sort sort = Sort.by(Direction.ASC, "id");
    when(repository.findAll(Example.of(entity), sort)).thenReturn(List.of());
    assertNotNull(service.findByExample(entity, sort));
  }

  @Test
  @RepeatedTest(REPEATED_TEST)
  void create() {
    assertNotNull(service.create(entity));
  }

  @Test
  @RepeatedTest(REPEATED_TEST)
  void saveAll() {
    when(repository.saveAll(any())).thenReturn(List.of(entity));
    assertNotNull(service.saveAll(List.of(entity)));
  }

  @Test
  @RepeatedTest(REPEATED_TEST)
  void update() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
    assertNotNull(service.update(entity));
  }

}
