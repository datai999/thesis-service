package com.thesis.service.repository.score;

import java.util.Collection;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterionRepository extends BaseRepository<CriterionTable> {

  Collection<CriterionTable> findByDeletedFalseAndParent(CriterionTable parent, Sort sort);

}
