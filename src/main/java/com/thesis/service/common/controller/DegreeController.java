package com.thesis.service.common.controller;

import com.thesis.service.common.model.SyDegreeTable;
import com.thesis.service.common.repository.SyDegreeRepository;
import com.thesis.service.common.service.DegreeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/degree")
@RequiredArgsConstructor
public class DegreeController
    extends AbstractBaseController<SyDegreeTable, SyDegreeRepository, DegreeService> {

}
