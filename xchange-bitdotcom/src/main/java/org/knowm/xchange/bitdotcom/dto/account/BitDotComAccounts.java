package org.knowm.xchange.bitdotcom.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitDotComAccounts {

  @JsonProperty("user_id")
  private int userId;

  @JsonProperty("created_at")
  private long createdAt;

  @JsonProperty("total_collateral")
  private BigDecimal totalCollateral;

  @JsonProperty("total_margin_balance")
  private BigDecimal totalMarginBalance;

  @JsonProperty("total_available")
  private BigDecimal totalAvailable;

  @JsonProperty("total_initial_margin")
  private BigDecimal totalInitialMargin;

  @JsonProperty("total_maintenance_margin")
  private BigDecimal totalMaintenanceMargin;

  @JsonProperty("total_initial_margin_ratio")
  private String totalInitialMarginRatio; // it may return "infinity"

  @JsonProperty("total_maintenance_margin_ratio")
  private String totalMaintenanceMarginRatio; // it may return "infinity"

  @JsonProperty("total_liability")
  private BigDecimal totalLiability;

  @JsonProperty("total_unsettled_amount")
  private BigDecimal totalUnsettledAmount;

  @JsonProperty("total_future_value")
  private BigDecimal totalFutureValue;

  @JsonProperty("total_option_value")
  private BigDecimal totalOptionValue;

  @JsonProperty("spot_orders_hc_loss")
  private BigDecimal spotOrdersHcLoss;

  @JsonProperty("total_position_pnl")
  private BigDecimal totalPositionPnl;

  @JsonProperty("details")
  private List<CurrencyLevelDetail> details;

  @JsonProperty("usdt_total_collateral")
  private BigDecimal usdtTotalCollateral;

  @JsonProperty("usdt_total_margin_balance")
  private BigDecimal usdtTotalMarginBalance;

  @JsonProperty("usdt_total_available")
  private BigDecimal usdtTotalAvailable;

  @JsonProperty("usdt_total_initial_margin")
  private BigDecimal usdtTotalInitialMargin;

  @JsonProperty("usdt_total_maintenance_margin")
  private BigDecimal usdtTotalMaintenanceMargin;

  @JsonProperty("usdt_total_initial_margin_ratio")
  private String usdtTotalInitialMarginRatio; // it may return "infinity"

  @JsonProperty("usdt_total_maintenance_margin_ratio")
  private String usdtTotalMaintenanceMarginRatio; // it may return "infinity"

  @JsonProperty("usdt_total_liability")
  private BigDecimal usdtTotalLiability;

  @JsonProperty("usdt_total_unsettled_amount")
  private BigDecimal usdtTotalUnsettledAmount;

  @JsonProperty("linear_pair_margins")
  private List<LinearPairMargin> linearPairMargins;

  // Include getters and setters for all properties
}
