package org.knowm.xchange.bitdotcom.dto;

import lombok.Data;

@Data
public class SystemTime {

  private long timestamp;

  public SystemTime(long timestamp) {
    this.timestamp = timestamp;
  }
}
