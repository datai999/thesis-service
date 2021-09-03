package com.thesis.service.service.system;

import com.thesis.service.model.system.SyDegreeTable;
import com.thesis.service.repository.system.SyDegreeRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DegreeService extends AbstractBaseService<SyDegreeTable, SyDegreeRepository> {

}
