package com.thesis.service.service.person;

import com.thesis.service.model.person.PersonBaseTable;
import com.thesis.service.repository.BaseRepository;
import com.thesis.service.service.ABaseService;

public abstract class APersonBaseService<T extends PersonBaseTable, R extends BaseRepository<T>>
    extends ABaseService<T, R> implements IPersonService<T> {
}
