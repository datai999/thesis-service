package com.thesis.service.service.topic;

import java.util.stream.Collectors;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilMemberService
    extends ABaseService<CouncilMemberTable, CouncilMemberRepository> {

  private final CouncilService councilService;

  public Object getCouncil(CouncilMemberTable entity, Sort sort) {
    var queryResult = this.repository.findAll(Example.of(entity), sort);
    return councilService.map(queryResult.parallelStream()
        .map(CouncilMemberTable::getCouncil).collect(Collectors.toList()));
  }

}
