package com.thesis.service.br.controller;

import javax.validation.Valid;

import com.thesis.service.br.model.BrSettingTable;
import com.thesis.service.br.service.SettingService;
import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.score.model.ScCriterionTemplateTable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingController extends ABaseController<BrSettingTable, SettingService> {

  @PostMapping("/topic-template")
  public <D extends ScCriterionTemplateTable> Object settingTopicTemplate(@RequestBody @Valid D requestBody) {
    return null;
  }

  @PostMapping("/thesis-template")
  public <D extends ScCriterionTemplateTable> Object settingThesisTemplate(@RequestBody @Valid D requestBody) {
    return null;
  }
}
