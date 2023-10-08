package org.knowm.xchange.bitdotcom.service;

import org.knowm.xchange.bitdotcom.BitDotComExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.BaseResilientExchangeService;

public class BitDotComAccountServiceRaw extends BitDotComBaseService {

  protected BitDotComAccountServiceRaw(BitDotComExchange exchange,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }
}
