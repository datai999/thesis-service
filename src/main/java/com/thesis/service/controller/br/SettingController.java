package com.thesis.service.controller.br;

import javax.validation.Valid;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.br.SettingTable;
import com.thesis.service.model.score.CriterionTemplateTable;
import com.thesis.service.service.br.SettingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setting")
public class SettingController extends ABaseController<SettingTable, SettingService> {

  @PostMapping("/template")
  public <D extends CriterionTemplateTable> Object settingTemplate(
      @RequestParam(defaultValue = "true") boolean thesis, @RequestBody @Valid D requestBody) {
    var type = thesis ? "setting.thesisTemplate" : "setting.topicTemplate";
    return super.service.setting(type, requestBody);
  }

  @GetMapping("/template")
  public Object getTemplate(@RequestParam(defaultValue = "true") boolean thesis) {
    var type = thesis ? "setting.thesisTemplate" : "setting.topicTemplate";
    return super.service.findByType(type);
  }

}