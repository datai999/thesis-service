package com.thesis.service.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.apache.commons.lang3.ObjectUtils;

public interface TimeConvert {

  static Date toDate(LocalDateTime time) {
    return Date.from(ObjectUtils.defaultIfNull(time, LocalDateTime.now())
        .atZone(ZoneId.systemDefault()).toInstant());
  }

}
