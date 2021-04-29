package com.thesis.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<E> extends JpaRepository<E, Long> {

}
