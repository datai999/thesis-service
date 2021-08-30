package com.thesis.service.common.service;

import com.thesis.service.common.model.SySubjectDepartmentTable;
import com.thesis.service.common.repository.SySubjectDepartmentRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectDepartmentService
    extends AbstractBaseService<SySubjectDepartmentTable, SySubjectDepartmentRepository> {

}
