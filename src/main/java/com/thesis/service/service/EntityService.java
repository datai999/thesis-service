package com.thesis.service.service;

import com.thesis.service.repository.person.PsTeacherRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntityService {

  public final PsTeacherRepository teacher;

}
