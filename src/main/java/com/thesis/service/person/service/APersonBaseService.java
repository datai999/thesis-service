package com.thesis.service.person.service;

import com.thesis.service.common.model.PersonBaseTable;
import com.thesis.service.common.repository.BaseRepository;
import com.thesis.service.common.service.ABaseService;

public abstract class APersonBaseService<T extends PersonBaseTable, R extends BaseRepository<T>>
    extends ABaseService<T, R> implements IPersonService<T> {
}
