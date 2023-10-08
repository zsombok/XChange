package org.knowm.xchange.bitdotcom.service;

import org.knowm.xchange.bitdotcom.BitDotComExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.BaseResilientExchangeService;

public class BitDotComTradeServiceRaw extends BitDotComBaseService {

  protected BitDotComTradeServiceRaw(BitDotComExchange exchange,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }
}
