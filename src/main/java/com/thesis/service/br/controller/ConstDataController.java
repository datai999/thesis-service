package com.thesis.service.br.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.thesis.service.br.ConstDataService;
import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.controller.ABaseController;

import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/const")
@RequiredArgsConstructor
public class ConstDataController extends ABaseController<BrConstDataTable, ConstDataService> {

  @Override
  @PostMapping
  public <D extends BrConstDataTable> Object save(@RequestBody D requestBody) {
    var exists = super.service.findAll(Example.of(requestBody));
    return exists.isEmpty() ? super.save(requestBody) : exists.get(0);
  }

  @Override
  @PostMapping("/all")
  public <D extends BrConstDataTable> Object saveAll(@RequestBody List<D> requestBody) {
    return Set.copyOf(requestBody).stream().map(x -> {
      var exists = super.service.findAll(Example.of(x));
      return exists.isEmpty() ? super.service.save(x) : exists.get(0);
    }).collect(Collectors.toSet());
  }

  @GetMapping("types")
  public Object findAllType() {
    return super.findAll().parallelStream().collect(Collectors.groupingBy(BrConstDataTable::getType));
  }

  @GetMapping("types/value")
  public Object findAllTypeValue() {
    return super.service.findAllType();
  }

}
