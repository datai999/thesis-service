package com.thesis.service.person.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.thesis.service.common.dto.DataBaseFieldConst;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.common.service.PageService;
import com.thesis.service.person.model.PsTeacherTable;
import com.thesis.service.person.repository.PsTeacherRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class TeacherService extends APersonBaseService<PsTeacherTable, PsTeacherRepository>
    implements PsTeacherRepository {

  @PersistenceContext
  private EntityManager entityManager;
  private final PageService pageService;

  @Override
  protected void preBuild(PsTeacherTable entity) {
    // do nothing
  }

  @Override
  public List<PsTeacherTable> searchIlikeName(String value) {
    return super.mainRepository.searchIlikeName(value);
  }

  @Override
  public List<PsTeacherTable> findAllByCode(Iterable<String> codes) {
    return super.mainRepository.findAllByCode(codes);
  }

  public Page<PsTeacherTable> search(SearchRequest requestBody) {

    String selectClause = "SELECT pT.* FROM ps_teacher pT";
    String groupClause = "GROUP BY pT.id";

    return pageService.search(
        requestBody,
        DataBaseFieldConst.TEACHER_ENTITY,
        PsTeacherTable.class,
        this,
        selectClause,
        groupClause);
  }

}
