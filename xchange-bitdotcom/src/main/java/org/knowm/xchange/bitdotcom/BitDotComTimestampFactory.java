package org.knowm.xchange.bitdotcom;

import si.mazi.rescu.SynchronizedValueFactory;

public class BitDotComTimestampFactory implements SynchronizedValueFactory<Long> {

  @Override
  public Long createValue() {
    return System.currentTimeMillis();
  }
}
