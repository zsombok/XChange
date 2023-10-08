package org.knowm.xchange.bitdotcom.service;

import org.knowm.xchange.bitdotcom.BitDotComExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class BitDotComMarketDataService extends BitDotComMarketDataServiceRaw
    implements MarketDataService {

  public BitDotComMarketDataService(
      BitDotComExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }
}
