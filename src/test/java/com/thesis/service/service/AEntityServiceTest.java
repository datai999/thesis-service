package com.thesis.service.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.thesis.service.dto.ModelConverter;
import com.thesis.service.model.BaseTable;
import com.thesis.service.repository.BaseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.util.ReflectionTestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AEntityServiceTest<T extends BaseTable, R extends BaseRepository<T>, S extends ABaseService<T, R>>
    extends ABaseServiceTest {

  protected R repository;
  protected S service;
  protected T entity;
  protected ModelConverter mapper;

  @BeforeAll
  @SuppressWarnings("unchecked")
  private void beforeAllService() {
    try {
      var entityClass = (Class<T>) this.getGenericType(0);
      this.entity = entityClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.entity.setId(0L);
    this.service = spyService();
    this.repository = mock((Class<R>) this.getGenericType(1));
    this.mapper = spy(new ModelConverter());
    ReflectionTestUtils.setField(this.service, "repository", this.repository);
    ReflectionTestUtils.setField(this.service, "mapper", mapper);
    ReflectionTestUtils.setField(this.service, "messageSource", mock(MessageSourceService.class));
    if (Objects.nonNull(service.mapping())) {
      when(service.mapping()).thenReturn(x -> new Object());
    }
  }

  protected abstract S spyService();

  private Type getGenericType(int index) {
    return ParameterizedType.class
        .cast(this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[index];
  }

  @Test
  void findById() {
    when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
    assertNotNull(service.findById(entity.getId()));
  }

  @Test
  void findByExample() {
    Sort sort = Sort.by(Direction.ASC, "id");
    when(repository.findAll(Example.of(entity), sort)).thenReturn(List.of());
    assertNotNull(service.findByExample(entity, sort));
  }

  @Test
  void create() {
    when(repository.save(any())).thenReturn(entity);
    assertNotNull(service.create(entity));
  }

  @Test
  void saveAll() {
    when(repository.saveAll(any())).thenReturn(List.of(entity));
    assertNotNull(service.saveAll(List.of(entity)));
  }

  @Test
  void update() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
    when(repository.save(any())).thenReturn(entity);
    assertNotNull(service.update(entity));
  }

}
