package com.thesis.service.controller.system;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.system.SySubjectDepartmentTable;
import com.thesis.service.repository.system.SySubjectDepartmentRepository;
import com.thesis.service.service.system.SubjectDepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subject-department")
@RequiredArgsConstructor
public class SubjectDepartmentController
    extends
    AbstractBaseController<SySubjectDepartmentTable, SySubjectDepartmentRepository, SubjectDepartmentService> {

}
