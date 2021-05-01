package com.thesis.service.br.controller;

import com.thesis.service.br.model.BrConstDataTable;
import com.thesis.service.common.controller.EntityController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/const")
@RequiredArgsConstructor
public class ConstDataController extends EntityController<BrConstDataTable> {

  @Override
  public String declareBaseService() {
    return "constData";
  }

}
