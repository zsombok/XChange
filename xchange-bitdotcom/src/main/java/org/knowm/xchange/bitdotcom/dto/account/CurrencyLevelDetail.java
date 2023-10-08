package org.knowm.xchange.bitdotcom.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyLevelDetail {

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("equity")
  private BigDecimal equity;

  @JsonProperty("liability")
  private BigDecimal liability;

  @JsonProperty("index_price")
  private BigDecimal indexPrice;

  @JsonProperty("usdt_index_price")
  private BigDecimal usdtIndexPrice;

  @JsonProperty("cash_balance")
  private BigDecimal cashBalance;

  @JsonProperty("margin_balance")
  private BigDecimal marginBalance;

  @JsonProperty("available_balance")
  private BigDecimal availableBalance;

  @JsonProperty("initial_margin")
  private BigDecimal initialMargin;

  @JsonProperty("spot_margin")
  private BigDecimal spotMargin;

  @JsonProperty("maintenance_margin")
  private BigDecimal maintenanceMargin;

  @JsonProperty("potential_liability")
  private BigDecimal potentialLiability;

  @JsonProperty("interest")
  private BigDecimal interest;

  @JsonProperty("interest_rate")
  private BigDecimal interestRate;

  @JsonProperty("pnl")
  private BigDecimal pnl;

  @JsonProperty("total_delta")
  private BigDecimal totalDelta;

  @JsonProperty("session_rpl")
  private BigDecimal sessionRpl;

  @JsonProperty("session_upl")
  private BigDecimal sessionUpl;

  @JsonProperty("option_value")
  private BigDecimal optionValue;

  @JsonProperty("option_pnl")
  private BigDecimal optionPnl;

  @JsonProperty("option_session_rpl")
  private BigDecimal optionSessionRpl;

  @JsonProperty("option_session_upl")
  private BigDecimal optionSessionUpl;

  @JsonProperty("option_delta")
  private BigDecimal optionDelta;

  @JsonProperty("option_gamma")
  private BigDecimal optionGamma;

  @JsonProperty("option_vega")
  private BigDecimal optionVega;

  @JsonProperty("option_theta")
  private BigDecimal optionTheta;

  @JsonProperty("future_value")
  private BigDecimal futureValue;

  @JsonProperty("future_pnl")
  private BigDecimal futurePnl;

  @JsonProperty("future_session_rpl")
  private BigDecimal futureSessionRpl;

  @JsonProperty("future_session_upl")
  private BigDecimal futureSessionUpl;

  @JsonProperty("future_session_funding")
  private BigDecimal futureSessionFunding;

  @JsonProperty("future_delta")
  private BigDecimal futureDelta;

  @JsonProperty("future_available_balance")
  private BigDecimal futureAvailableBalance;

  @JsonProperty("option_available_balance")
  private BigDecimal optionAvailableBalance;

  @JsonProperty("unsettled_amount")
  private BigDecimal unsettledAmount;
}
