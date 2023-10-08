package org.knowm.xchange.bitdotcom.service;

import org.knowm.xchange.bitdotcom.BitDotComExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.trade.TradeService;

public class BitDotComTradeService extends BitDotComMarketDataServiceRaw
    implements TradeService {

  public BitDotComTradeService(
      BitDotComExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }
}
