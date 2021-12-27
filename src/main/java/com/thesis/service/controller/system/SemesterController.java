package com.thesis.service.controller.system;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.system.SemesterPropertyTable;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.service.system.SemesterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/semesters")
public class SemesterController extends ABaseController<SemesterTable, SemesterService> {

  @GetMapping("/current")
  public Object getCurrentSemester() {
    return super.service.map(super.service.getCurrentSemester());
  }

  @GetMapping("/in-create-time")
  public boolean inCreateTime(@RequestParam(defaultValue = "true") boolean thesis) {
    return super.service.inCreateTime(thesis);
  }

  @GetMapping("/in-any-create-time")
  public Object inAnyCreateTime() {
    return this.inCreateTime(false) || this.inCreateTime(true);
  }

  @GetMapping("/in-register-time")
  public Object inRegisterTopicTime(@RequestParam(defaultValue = "true") boolean thesis) {
    return super.service.inRegisterTopicTime(thesis);
  }

  @GetMapping("/in-mid-mark-time")
  public Object inMidMarkTime(@RequestParam(defaultValue = "true") boolean thesis) {
    return super.service.inMidMarkTime(thesis);
  }

  @GetMapping("/compare-mid-mark-start-time")
  public Object compareMidMarkStartTime(@RequestParam(defaultValue = "true") boolean thesis,
      @RequestParam boolean before) {
    return super.service.compare(thesis, before, SemesterPropertyTable::getMidMarkStart);
  }

  @GetMapping("/compare-mid-mark-end-time")
  public Object compareMidMarkEndTime(
      @RequestParam(defaultValue = "true") boolean thesis,
      @RequestParam boolean before) {
    return super.service.compare(thesis, before, SemesterPropertyTable::getMidMarkEnd);
  }

  @PutMapping("/current")
  public Object setCurrentSemester(@RequestParam Long id) {
    return super.service.setCurrentSemester(id);
  }

}
