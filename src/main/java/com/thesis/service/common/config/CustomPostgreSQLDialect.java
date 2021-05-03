package com.thesis.service.common.config;

import java.sql.Types;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;

import org.hibernate.dialect.PostgreSQL10Dialect;

public class CustomPostgreSQLDialect extends PostgreSQL10Dialect {

  public CustomPostgreSQLDialect() {
    super();
    this.registerHibernateType(Types.ARRAY, StringArrayType.class.getName());
    this.registerHibernateType(Types.OTHER, JsonNodeBinaryType.class.getName());
  }

}
