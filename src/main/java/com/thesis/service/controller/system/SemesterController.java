package com.thesis.service.controller.system;

import com.thesis.service.controller.ABaseController;
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

  @GetMapping("/in-any-create-time")
  public Object inAnyCreateTime() {
    return super.service.inAnyCreateTime();
  }

  @GetMapping("/in-register-time")
  public Object inRegisterTopicTime(@RequestParam boolean thesis) {
    return super.service.inRegisterTopicTime(thesis);
  }

  @GetMapping("/before-mid-mark-start-time")
  public Object beforeMidMarkStartTime(@RequestParam boolean thesis) {
    return super.service.beforeMidMarkStartTime(thesis);
  }

  @GetMapping("/before-mid-mark-end-time")
  public Object beforeMidMarkEndTime(@RequestParam boolean thesis) {
    return super.service.beforeMidMarkEndTime(thesis);
  }

  @PutMapping("/current")
  public Object setCurrentSemester(@RequestParam Long id) {
    return super.service.setCurrentSemester(id);
  }

}
