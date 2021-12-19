package com.thesis.service.service.system.testsuite;

import java.util.function.Supplier;
import com.thesis.service.model.system.SemesterTable;

public interface SemesterServiceTS {

  String CURRENT_SEMESTER_NAME = "203";

  Supplier<SemesterTable> PREVIOUS_SEMESTER =
      () -> new SemesterTable().setName("202");
  Supplier<SemesterTable> CURRENT_SEMESTER =
      () -> SemesterTable.builder().id(3L).name(CURRENT_SEMESTER_NAME).build();

}
