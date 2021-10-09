package com.thesis.service.repository;

import java.util.List;
import com.thesis.service.model.BaseTable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseTable>
    extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

  List<E> findByDeletedFalse(Sort sort);

  @Transactional
  @Modifying
  @Query("UPDATE #{#entityName} SET deleted = TRUE WHERE id = ?1")
  void deleteLogic(long id);

}
