package com.thesis.service.common.repository;

import com.thesis.service.common.model.BaseTable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<E extends BaseTable> extends JpaRepository<E, Long> {

}
