package org.knowm.xchange.bitdotcom.service;

import org.knowm.xchange.bitdotcom.BitDotComExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.service.account.AccountService;

public class BitDotComAccountService extends BitDotComMarketDataServiceRaw
    implements AccountService {

  public BitDotComAccountService(
      BitDotComExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }
}
