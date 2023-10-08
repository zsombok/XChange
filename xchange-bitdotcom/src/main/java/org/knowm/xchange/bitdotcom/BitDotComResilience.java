package org.knowm.xchange.bitdotcom;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import java.time.Duration;
import org.knowm.xchange.client.ResilienceRegistries;

public final class BitDotComResilience {

  public static final String ORDERS_PER_SECOND_RATE_LIMITER = "ordersPerSecond";

  private BitDotComResilience() {}

  public static ResilienceRegistries createRegistries() {
    ResilienceRegistries registries = new ResilienceRegistries();
    registries
        .rateLimiters()
        .rateLimiter(
            ORDERS_PER_SECOND_RATE_LIMITER,
            RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(5)
                .build());
    return registries;
  }
}
