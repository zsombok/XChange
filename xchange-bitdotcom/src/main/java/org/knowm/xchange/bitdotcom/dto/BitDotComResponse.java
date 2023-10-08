package org.knowm.xchange.bitdotcom.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class BitDotComResponse<R> {

  @JsonProperty("code")
  int code;

  @JsonProperty("message")
  String message;

  @JsonProperty("data")
  R data;

  @JsonProperty("page_info")
  BitDotComPageInfo pageInfo;

  @Data
  public static class BitDotComPageInfo {

    @JsonProperty("has_more")
    boolean hasMore;
  }
}
