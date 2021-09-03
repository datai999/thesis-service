package com.thesis.service.service;

import com.thesis.service.model.BaseTable;

public interface IService<E extends BaseTable> {
  E build(E entity);
}
