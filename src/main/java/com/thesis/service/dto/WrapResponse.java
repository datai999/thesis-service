package com.thesis.service.dto;

import com.thesis.service.utils.ContextAccessor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WrapResponse {

  private Object data;
  private String errorCode;
  private String errorMessage;
  private Object error;

  public static WrapResponse data(Object data) {
    return new WrapResponse(data, null, null, null);
  }

  public static WrapResponse error(Object error) {
    return new WrapResponse(null, null, null, error);
  }

  public static WrapResponse error(Object errorCode, Object error) {
    return new WrapResponse(null, errorCode.toString(), null, error);
  }

  public static WrapResponse errorCode(String code, Object... args) {
    var message = ContextAccessor.getBean(MessageSource.class)
        .getMessage(code, args, LocaleContextHolder.getLocale());
    return new WrapResponse(null, code, message, null);
  }

}
