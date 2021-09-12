package com.thesis.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WrapResponse {

  private Object data;
  private String errorCode;
  private Object error;

  public static WrapResponse data(Object data) {
    return new WrapResponse(data, null, null);
  }

  public static WrapResponse error(Object error) {
    return new WrapResponse(null, null, error);
  }

  public static WrapResponse error(Object errorCode, Object error) {
    return new WrapResponse(null, errorCode.toString(), error);
  }

}
