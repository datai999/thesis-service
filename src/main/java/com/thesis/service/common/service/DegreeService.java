package com.thesis.service.common.service;

import com.thesis.service.common.model.SyDegreeTable;
import com.thesis.service.common.repository.SyDegreeRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DegreeService extends AbstractBaseService<SyDegreeTable, SyDegreeRepository> {

}
