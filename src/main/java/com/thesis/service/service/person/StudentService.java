package com.thesis.service.service.person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.thesis.service.dto.SearchRequest;
import com.thesis.service.model.person.StudentTable;
import com.thesis.service.repository.person.StudentRepository;
import com.thesis.service.service.AbstractBaseService;
import com.thesis.service.service.PageService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class StudentService extends AbstractBaseService<StudentTable, StudentRepository> {

  @PersistenceContext
  private EntityManager entityManager;
  private final PageService pageService;


  public Page<StudentTable> search(SearchRequest requestBody) {

    String selectClause = "SELECT psS.* FROM ps_student psS "
        + "LEFT JOIN br_const_data bCD_gender ON bCD_gender.id = psS.gender_id "
        + "LEFT JOIN br_const_data bCD_major ON bCD_major.id = psS.major_id "
        + "LEFT JOIN br_const_data bCD_edu_method ON bCD_edu_method.id = psS.education_method_id ";

    String groupClause = "GROUP BY psS.id";

    // TODO: fix
    return null;

    // return pageService.search(
    // requestBody,
    // DataBaseFieldConst.STUDENT_ENTITY,
    // StudentTable.class,
    // this,
    // selectClause,
    // groupClause);
  }
}