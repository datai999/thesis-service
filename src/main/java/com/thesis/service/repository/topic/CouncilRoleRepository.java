package com.thesis.service.repository.topic;

import java.util.List;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilRoleRepository extends BaseRepository<CouncilRoleTable> {

  List<CouncilRoleTable> findByDeletedFalseOrderByDisplayOrder();

}
