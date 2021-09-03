package com.thesis.service.service.system;

import com.thesis.service.model.system.DegreeTable;
import com.thesis.service.repository.system.DegreeRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DegreeService extends AbstractBaseService<DegreeTable, DegreeRepository> {

}
