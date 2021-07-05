package com.thesis.service.person.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.thesis.service.common.dto.DataBaseFieldConst;
import com.thesis.service.common.dto.request.SearchRequest;
import com.thesis.service.common.service.PageService;
import com.thesis.service.person.model.PsStudentTable;
import com.thesis.service.person.repository.PsStudentRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class StudentService extends APersonBaseService<PsStudentTable, PsStudentRepository>
    implements PsStudentRepository {

  @PersistenceContext
  private EntityManager entityManager;
  private final PageService pageService;

  @Override
  protected void preBuild(PsStudentTable entity) {
    // do nothing
  }

  @Override
  public List<PsStudentTable> searchIlikeNameOrCode(String value) {
    return super.mainRepository.searchIlikeNameOrCode(value);
  }

  @Override
  public List<PsStudentTable> findAllByCode(Iterable<String> codes) {
    return super.mainRepository.findAllByCode(codes);
  }

  public Page<PsStudentTable> search(SearchRequest requestBody) {

    String selectClause = "SELECT psS.* FROM ps_student psS "
        + "LEFT JOIN br_const_data bCD_gender ON bCD_gender.id = psS.gender_id "
        + "LEFT JOIN br_const_data bCD_major ON bCD_major.id = psS.major_id "
        + "LEFT JOIN br_const_data bCD_edu_method ON bCD_edu_method.id = psS.education_method_id ";

    String groupClause = "GROUP BY psS.id";

    return pageService.search(
        requestBody,
        DataBaseFieldConst.STUDENT_ENTITY,
        PsStudentTable.class,
        this,
        selectClause,
        groupClause);
  }
}
