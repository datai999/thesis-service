package com.thesis.service.controller.topic;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.service.topic.CouncilRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/council-roles")
public class CouncilRoleController extends ABaseController<CouncilRoleTable, CouncilRoleService> {

}
