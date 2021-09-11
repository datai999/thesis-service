package com.thesis.service.controller.system;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.service.system.EducationMethodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/education-methods")
@RequiredArgsConstructor
public class EducationMethodController
    extends
    AbstractBaseController<EducationMethodTable, EducationMethodService> {

}
