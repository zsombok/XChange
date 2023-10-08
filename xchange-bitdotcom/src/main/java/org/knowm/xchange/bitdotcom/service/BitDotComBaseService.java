package org.knowm.xchange.bitdotcom.service;

import java.io.IOException;
import org.knowm.xchange.bitdotcom.BitDotCom;
import org.knowm.xchange.bitdotcom.BitDotComAuthenticated;
import org.knowm.xchange.bitdotcom.BitDotComExchange;
import org.knowm.xchange.bitdotcom.dto.BitDotComResponse;
import org.knowm.xchange.bitdotcom.dto.SystemTime;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.BaseResilientExchangeService;
import si.mazi.rescu.SynchronizedValueFactory;

public class BitDotComBaseService extends BaseResilientExchangeService<BitDotComExchange> {

  protected final String apiKey;
  protected final BitDotCom bitDotCom;
  protected final BitDotComAuthenticated bitDotComAuthenticated;

  //  protected final ParamsDigest signatureCreator;

  protected BitDotComBaseService(
      BitDotComExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
    this.bitDotCom =
        ExchangeRestProxyBuilder.forInterface(BitDotCom.class, exchange.getExchangeSpecification())
            .build();
    this.bitDotComAuthenticated =
        ExchangeRestProxyBuilder.forInterface(
                BitDotComAuthenticated.class, exchange.getExchangeSpecification())
            .build();
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    //    this.signatureCreator =
    //
    // BitDotComHmacDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  public Long getRecvWindow() {
    Object obj =
        exchange.getExchangeSpecification().getExchangeSpecificParametersItem("recvWindow");
    if (obj == null) return null;
    if (obj instanceof Number) {
      long value = ((Number) obj).longValue();
      if (value < 0 || value > 60000) {
        throw new IllegalArgumentException(
            "Exchange-specific parameter \"recvWindow\" must be in the range [0, 60000].");
      }
      return value;
    }
    if (obj.getClass().equals(String.class)) {
      try {
        return Long.parseLong((String) obj, 10);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(
            "Exchange-specific parameter \"recvWindow\" could not be parsed.", e);
      }
    }
    throw new IllegalArgumentException(
        "Exchange-specific parameter \"recvWindow\" could not be parsed.");
  }

  public SynchronizedValueFactory<Long> getTimestampFactory() {
    return exchange.getTimestampFactory();
  }

  public BitDotComResponse<SystemTime> getSystemTime() throws IOException {
    return decorateApiCall(bitDotCom::systemTime).withRetry(retry("systemTime")).call();
  }
}
