package org.knowm.xchange.bitdotcom.service;

import static org.knowm.xchange.bitdotcom.BitDotComAdapters.adaptAccountInfo;

import java.io.IOException;
import org.knowm.xchange.bitdotcom.BitDotComExchange;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;

public class BitDotComAccountService extends BitDotComMarketDataServiceRaw
    implements AccountService {

  public BitDotComAccountService(
      BitDotComExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    return adaptAccountInfo(
        bitDotComAuthenticated
            .umAccounts(apiKey, getTimestampFactory(), signatureCreator, null)
            .getData());
  }
}
