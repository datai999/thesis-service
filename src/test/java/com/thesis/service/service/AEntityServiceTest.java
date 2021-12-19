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
import java.util.function.Consumer;
import java.util.function.Supplier;
import com.thesis.service.model.BaseTable;
import com.thesis.service.repository.BaseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.util.ReflectionTestUtils;

public abstract class AEntityServiceTest<T extends BaseTable, R extends BaseRepository<T>, S extends ABaseService<T, R>>
    extends ABaseServiceTest {

  private static final int REPEATED_TEST = 3;

  protected R repository;
  protected S service;
  protected T entity;
  protected Supplier<T> entitySupplier = () -> entity;

  protected abstract S spyService();

  protected Consumer<T> extendEntity() {
    return (T entity) -> {
    };
  }

  private Type getGenericType(int index) {
    return ParameterizedType.class
        .cast(this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[index];
  }

  @BeforeAll
  @SuppressWarnings("unchecked")
  private void beforeAllService() {
    this.repository = mock((Class<R>) this.getGenericType(1));
    when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

    this.service = spyService();
    ReflectionTestUtils.setField(this.service, "repository", this.repository);
    ReflectionTestUtils.setField(this.service, "mapper", super.mapper);
    ReflectionTestUtils.setField(this.service, "messageSource", super.messageSource);
  }

  @BeforeEach
  @SuppressWarnings("unchecked")
  private void beforeEachService() {
    var entityClass = (Class<T>) this.getGenericType(0);
    try {
      this.entity = entityClass.getDeclaredConstructor().newInstance();
      this.entity.setId(0L);
      this.extendEntity().accept(this.entity);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @RepeatedTest(REPEATED_TEST)
  void findById() {
    when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
    assertNotNull(service.findById(entity.getId()));
  }

  @RepeatedTest(REPEATED_TEST)
  void findByExample_empty() {
    Sort sort = Sort.by(Direction.ASC, "id");
    when(repository.findAll(Example.of(entity), sort)).thenReturn(List.of());
    assertNotNull(service.findByExample(entity, sort));
  }

  @RepeatedTest(REPEATED_TEST)
  void findByExample_one() {
    Sort sort = Sort.by(Direction.ASC, "id");
    when(repository.findAll(Example.of(entity), sort)).thenReturn(List.of(entity));
    assertNotNull(service.findByExample(entity, sort));
  }

  @RepeatedTest(REPEATED_TEST)
  void create() {
    assertNotNull(service.create(entity));
  }

  @RepeatedTest(REPEATED_TEST)
  void saveAll() {
    when(repository.saveAll(any())).thenReturn(List.of(entity));
    assertNotNull(service.saveAll(List.of(entity)));
  }

  @RepeatedTest(REPEATED_TEST)
  void update() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
    assertNotNull(service.update(entity));
  }

}
