package org.knowm.xchange.bitdotcom.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinearPairMargin {

  @JsonProperty("pair")
  private String pair;

  @JsonProperty("initial_margin")
  private BigDecimal initialMargin;

  @JsonProperty("maintenance_margin")
  private BigDecimal maintenanceMargin;
}
