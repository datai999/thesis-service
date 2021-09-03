package com.thesis.service.service.person;

import com.thesis.service.model.person.PsTeacherTable;
import com.thesis.service.repository.person.PsTeacherRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService
    extends AbstractBaseService<PsTeacherTable, PsTeacherRepository> {

}
