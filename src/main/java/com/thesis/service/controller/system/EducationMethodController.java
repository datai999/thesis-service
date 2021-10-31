package com.thesis.service.controller.system;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.service.system.EducationMethodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/education-methods")
public class EducationMethodController
    extends
    ABaseController<EducationMethodTable, EducationMethodService> {

}
