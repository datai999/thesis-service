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

  @GetMapping("/allow-student-register-cancel")
  public Object allowStudentRegisterCancelTopic() {
    return super.service.allowStudentRegisterCancelTopic();
  }

  @PutMapping("/current")
  public Object setCurrentSemester(@RequestParam Long id) {
    return super.service.setCurrentSemester(id);
  }

}
