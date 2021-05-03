package com.thesis.service.br.dto;

import java.util.List;

public interface TypeValueDto {

  final String QUERY = "SELECT type, ARRAY_AGG(CAST(id AS VARCHAR)) AS arrId, ARRAY_AGG(value) AS arrValue FROM br_const_data GROUP BY type";

  List<String> getArrId();

  String getType();

  List<String> getArrValue();

}
