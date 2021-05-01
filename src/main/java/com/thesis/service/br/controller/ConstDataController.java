package com.thesis.service.br.controller;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.br.repository.BrConstDataRepository;
import com.thesis.service.common.controller.EntityController;
import com.thesis.service.common.dto.response.WrapResponse;

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
public class ConstDataController extends EntityController<BrConstDataTable, BrConstDataRepository> {

  @Override
  public String declareBaseService() {
    return "constData";
  }

  @Override
  @PostMapping
  public <D extends BrConstDataTable> Object save(@RequestBody D requestBody) {
    var notExists = super.repository.findAll(Example.of(requestBody)).isEmpty();
    return notExists ? super.save(requestBody) : WrapResponse.error("Const is existed");
  }

  @GetMapping("types")
  public Object findAllType() {
    return WrapResponse.data(super.repository.findAllType());
  }

}
