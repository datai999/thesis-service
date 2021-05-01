package com.thesis.service.br.controller;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.br.repository.BrConstDataRepository;
import com.thesis.service.common.controller.EntityController;
import com.thesis.service.common.dto.response.WrapResponse;

import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("types")
  public Object findAllType() {
    return WrapResponse.data(super.repository.findAllType());
  }

}
