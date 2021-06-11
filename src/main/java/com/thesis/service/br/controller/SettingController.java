package com.thesis.service.br.controller;

import javax.validation.Valid;

import com.thesis.service.br.model.BrSettingTable;
import com.thesis.service.br.service.SettingService;
import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.score.model.ScCriterionTemplateTable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setting")
public class SettingController extends ABaseController<BrSettingTable, SettingService> {

  @PostMapping("/template")
  public <D extends ScCriterionTemplateTable> Object settingTopicTemplate(
      @RequestParam(defaultValue = "true") boolean thesis, @RequestBody @Valid D requestBody) {
    var type = thesis ? "setting.thesisTemplate" : "setting.topicTemplate";
    return super.service.setting(type, requestBody);
  }

}
