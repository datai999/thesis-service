package com.thesis.service.controller;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import com.thesis.service.dto.DataBaseFieldConst;
import com.thesis.service.dto.SearchRequest;
import com.thesis.service.model.BaseTable;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public abstract class AbstractBaseController<T extends BaseTable, S extends AbstractBaseService<T, ?>> {

  @Autowired
  protected S service;

  @GetMapping
  public Object findAll(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.getRepository().findAll(sortable);
  }

  @GetMapping("/paging")
  public Object findPaging(
      @RequestParam(defaultValue = "1") @Positive Integer page,
      @RequestParam(defaultValue = "5") @Positive Integer size,
      @RequestParam(defaultValue = "DESC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    Pageable pageable = PageRequest.of(page - 1, size, sortable);
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
  public Object save(@RequestBody @Valid T requestBody) {
    return service.getRepository().save(requestBody);
  }

  @PostMapping("/all")
  public Object saveAll(@RequestBody List<T> requestBody) {
    return service.getRepository().saveAll(requestBody);
  }

  @PostMapping("/example")
  public Object findAll(@RequestBody T entity) {
    return service.getRepository().findAll(Example.of(entity));
  }

  @DeleteMapping
  public Object deleteAll() {
    service.getRepository().deleteAll();
    return true;
  }

}
