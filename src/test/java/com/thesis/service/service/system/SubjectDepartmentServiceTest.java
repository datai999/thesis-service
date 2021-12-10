package com.thesis.service.service.system;

import static org.mockito.Mockito.spy;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.repository.system.SubjectDepartmentRepository;
import com.thesis.service.service.AEntityServiceTest;

public class SubjectDepartmentServiceTest
    extends
    AEntityServiceTest<SubjectDepartmentTable, SubjectDepartmentRepository, SubjectDepartmentService> {

  @Override
  protected SubjectDepartmentService spyService() {
    return spy(new SubjectDepartmentService());
  }

}
