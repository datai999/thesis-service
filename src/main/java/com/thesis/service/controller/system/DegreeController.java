package com.thesis.service.controller.system;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.service.system.DegreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/degree")
@RequiredArgsConstructor
public class DegreeController
    extends AbstractBaseController<DegreeTable, DegreeService> {

}
