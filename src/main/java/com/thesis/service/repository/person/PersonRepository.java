package com.thesis.service.repository.person;

import com.thesis.service.model.person.PersonTable;
import com.thesis.service.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<PersonTable> {

  PersonTable findByEmail(String email);
}
