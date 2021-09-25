package com.thesis.service.repository;

import java.util.List;
import com.thesis.service.model.BaseTable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends BaseTable>
    extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

  List<E> findByDeletedFalse(Sort sort);

}
