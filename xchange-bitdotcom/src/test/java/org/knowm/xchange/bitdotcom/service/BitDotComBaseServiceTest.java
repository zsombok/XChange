package org.knowm.xchange.bitdotcom.service;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitdotcom.BitDotComExchange;
import org.knowm.xchange.bitdotcom.dto.BitDotComResponse;
import org.knowm.xchange.bitdotcom.dto.SystemTime;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class BitDotComBaseServiceTest {

  BitDotComExchange exchange;

  @Before
  public void setUp() throws Exception {
    creataExchange();
  }

  private void creataExchange() {
    exchange = ExchangeFactory.INSTANCE.createExchangeWithoutSpecification(BitDotComExchange.class);
    ExchangeSpecification spec = exchange.getDefaultExchangeSpecification();
    exchange.applySpecification(spec);
  }

  @Test
  public void getSystemTime() throws IOException {
    MarketDataService marketDataService = exchange.getMarketDataService();
    BitDotComResponse<SystemTime> systemTime = ((BitDotComMarketDataService) marketDataService).getSystemTime();
    assertNotNull(systemTime);
    System.out.println(systemTime);
  }
}
