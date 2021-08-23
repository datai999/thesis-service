package com.thesis.service.common.controller;

import com.thesis.service.common.model.SyMajorTable;
import com.thesis.service.common.service.MajorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/major")
@RequiredArgsConstructor
public class MajorController extends ABaseController<SyMajorTable, MajorService> {

}
