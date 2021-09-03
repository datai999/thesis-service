package com.thesis.service.service.system;

import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.repository.system.SubjectDepartmentRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectDepartmentService
    extends AbstractBaseService<SubjectDepartmentTable, SubjectDepartmentRepository> {

}
