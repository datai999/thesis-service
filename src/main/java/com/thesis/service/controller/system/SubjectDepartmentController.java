package com.thesis.service.controller.system;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.repository.system.SubjectDepartmentRepository;
import com.thesis.service.service.system.SubjectDepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subject-department")
@RequiredArgsConstructor
public class SubjectDepartmentController
    extends
    AbstractBaseController<SubjectDepartmentTable, SubjectDepartmentRepository, SubjectDepartmentService> {

}
