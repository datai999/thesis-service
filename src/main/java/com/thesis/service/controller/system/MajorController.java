package com.thesis.service.controller.system;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.service.system.MajorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/majors")
public class MajorController extends ABaseController<MajorTable, MajorService> {

}
