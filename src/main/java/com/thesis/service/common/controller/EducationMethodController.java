package com.thesis.service.common.controller;

import com.thesis.service.common.model.SyEducationMethodTable;
import com.thesis.service.common.repository.SyEducationMethodRepository;
import com.thesis.service.common.service.EducationMethodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/education-methods")
@RequiredArgsConstructor
public class EducationMethodController
    extends
    AbstractBaseController<SyEducationMethodTable, SyEducationMethodRepository, EducationMethodService> {

}
