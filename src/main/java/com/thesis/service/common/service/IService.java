package com.thesis.service.common.service;

import com.thesis.service.common.model.BaseTable;

public interface IService<E extends BaseTable> {
  E build(E entity);
}
