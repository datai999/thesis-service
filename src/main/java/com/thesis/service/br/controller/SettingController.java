package com.thesis.service.br.controller;

import com.thesis.service.br.model.BrSettingTable;
import com.thesis.service.br.service.SettingService;
import com.thesis.service.common.controller.ABaseController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingController extends ABaseController<BrSettingTable, SettingService> {

}
