package com.thesis.service.common.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import com.thesis.service.common.dto.DataBaseFieldConst;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.common.model.BaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public abstract class AbstractBaseController<T extends BaseTable, R extends BaseRepository<T>, S extends AbstractBaseService<T, R>> {

  @Autowired
  protected S service;

  @GetMapping
  public List<T> findAll() {
    return service.getRepository().findAll().stream().collect(Collectors.toList());
  }

  @GetMapping("/paging")
  public Object findPaging(@RequestParam @NotNull @PositiveOrZero Integer number,
      @RequestParam @NotNull @Positive Integer size, Optional<String> sort,
      @RequestParam(defaultValue = "false") boolean descend) {

    Pageable pageable = null;

    if (sort.isPresent()) {
      Sort sortable = descend ? Sort.by(sort.get()).descending() : Sort.by(sort.get());
      pageable = PageRequest.of(number, size, sortable);
    } else {
      pageable = PageRequest.of(number, size);
    }

    return service.getRepository().findAll(pageable);
  }

  @PostMapping("/paging/search")
  public Object search(@RequestBody @Valid SearchRequest requestBody) {

    Pageable pageable =
        PageRequest.of(requestBody.getPage().getNumber(), requestBody.getPage().getSize());

    if (!Objects.isNull(requestBody.getSort())
        && !Objects.isNull(requestBody.getSort().getField())) {

      String dbField = DataBaseFieldConst.MODAL.get(requestBody.getSort().getField());
      if (Objects.isNull(dbField)) {
        dbField = requestBody.getSort().getField();
      }

      Sort sortable = Sort.by(dbField);
      sortable = requestBody.getSort().getDescend() ? sortable.descending() : sortable.ascending();
      pageable = PageRequest.of(requestBody.getPage().getNumber(), requestBody.getPage().getSize(),
          sortable);
    }

    return service.getRepository().findAll(pageable);
  }

  @PostMapping
  public T save(@RequestBody @Valid T requestBody) {
    return service.getRepository().save(requestBody);
  }

  @PostMapping("/all")
  public List<T> saveAll(@RequestBody List<T> requestBody) {
    return service.getRepository().saveAll(requestBody);
  }

  @PostMapping("/example")
  public List<T> findAll(@RequestBody T entity) {
    return service.getRepository().findAll(Example.of(entity));
  }

  @DeleteMapping
  public Object deleteAll() {
    service.getRepository().deleteAll();
    return true;
  }

}
