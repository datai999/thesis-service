package com.thesis.service.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public interface TimeConvert {

  static Date toDate(LocalDateTime time) {
    return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
  }

}
