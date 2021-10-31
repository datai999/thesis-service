package com.thesis.service.controller.system;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.service.system.DegreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/degrees")
@RequiredArgsConstructor
public class DegreeController
    extends ABaseController<DegreeTable, DegreeService> {

}
