package com.thesis.service.service.topic;

import java.util.stream.Collectors;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.CouncilMemberRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouncilMemberService
    extends ABaseService<CouncilMemberTable, CouncilMemberRepository> {

  private final CouncilService councilService;

  public Object findByMemberId(Long memberId, Sort sort) {
    long id = ObjectUtils.defaultIfNull(memberId, super.getAuth().getId());
    var entity = new CouncilMemberTable().setMember(new UserTable().setId(id));
    var example = Example.of(entity);
    var queryResult = this.repository.findAll(example, sort);
    return councilService.map(queryResult.parallelStream()
        .map(CouncilMemberTable::getCouncil).distinct().collect(Collectors.toList()));
  }

}
