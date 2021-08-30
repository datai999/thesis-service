package com.thesis.service.person.service;

import com.thesis.service.common.service.AbstractBaseService;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.person.repository.PsTeacherRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService
    extends AbstractBaseService<PsTeacherTable, PsTeacherRepository> {

}
