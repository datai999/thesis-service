package com.thesis.service.common.controller;

import com.thesis.service.common.model.SySubjectDepartmentTable;
import com.thesis.service.common.repository.SySubjectDepartmentRepository;
import com.thesis.service.common.service.SubjectDepartmentService;
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
