package com.thesis.service.topic.service;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.topic.model.TpCouncilTable;
import com.thesis.service.topic.repository.TpCouncilRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilService extends ABaseService<TpCouncilTable, TpCouncilRepository> {

}
