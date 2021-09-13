package com.thesis.service.repository;

import com.thesis.service.model.BaseTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseRepository<E extends BaseTable>
    extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

}
