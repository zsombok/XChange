package org.knowm.xchange.bitdotcom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;

public class BitDotComExchangeTest {

  Exchange exchange;

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
  public void test() {
    System.out.println(exchange.getExchangeMetaData());
  }
}
