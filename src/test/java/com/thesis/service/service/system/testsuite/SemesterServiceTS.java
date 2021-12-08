package com.thesis.service.service.system.testsuite;

import java.util.function.Supplier;
import com.thesis.service.model.system.SemesterTable;

public interface SemesterServiceTS {

  String PREVIOUS_SEMESTER_NAME = "202";
  String CURRENT_SEMESTER_NAME = "203";

  Supplier<SemesterTable> PREVIOUS_SEMESTER =
      () -> new SemesterTable().setName(PREVIOUS_SEMESTER_NAME);
  Supplier<SemesterTable> CURRENT_SEMESTER =
      () -> new SemesterTable().setName(CURRENT_SEMESTER_NAME);

}
