package org.knowm.xchange.bitdotcom;

import static org.knowm.xchange.bitdotcom.BitDotComConstants.API_BASE_URL;
import static org.knowm.xchange.bitdotcom.BitDotComConstants.SANDBOX_BASE_HOST;
import static org.knowm.xchange.bitdotcom.BitDotComConstants.SANDBOX_BASE_URL;
import static org.knowm.xchange.bitdotcom.BitDotComConstants.SPECIFIC_PARAM_USE_SANDBOX;

import lombok.Getter;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitdotcom.service.BitDotComAccountService;
import org.knowm.xchange.bitdotcom.service.BitDotComMarketDataService;
import org.knowm.xchange.bitdotcom.service.BitDotComTradeService;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.AuthUtils;
import si.mazi.rescu.SynchronizedValueFactory;

@Getter
public class BitDotComExchange extends BaseExchange implements Exchange {
  protected static ResilienceRegistries RESILIENCE_REGISTRIES;
  protected SynchronizedValueFactory<Long> timestampFactory;

  public static void resetResilienceRegistries() {
    RESILIENCE_REGISTRIES = null;
  }

  /** Adjust host parameters depending on exchange specific parameters */
  private static void concludeHostParams(ExchangeSpecification exchangeSpecification) {
    if (exchangeSpecification.getExchangeSpecificParameters() != null) {
      if (enabledSandbox(exchangeSpecification)) {
        exchangeSpecification.setSslUri(SANDBOX_BASE_URL);
        exchangeSpecification.setHost(SANDBOX_BASE_HOST);
      }
    }
  }

  private static boolean enabledSandbox(ExchangeSpecification exchangeSpecification) {
    return Boolean.TRUE.equals(
        exchangeSpecification.getExchangeSpecificParametersItem(SPECIFIC_PARAM_USE_SANDBOX));
  }

  @Override
  protected void initServices() {
    //    this.timestampFactory = new
    // BitDotComTimestampFactory(getExchangeSpecification().getResilience(),
    // getResilienceRegistries());
    this.marketDataService = new BitDotComMarketDataService(this, getResilienceRegistries());
    this.tradeService = new BitDotComTradeService(this, getResilienceRegistries());
    this.accountService = new BitDotComAccountService(this, getResilienceRegistries());
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    throw new UnsupportedOperationException(
        "BitDotCom uses timestamp/recvwindow rather than a nonce");
  }

  @Override
  public ResilienceRegistries getResilienceRegistries() {
    if (RESILIENCE_REGISTRIES == null) {
      RESILIENCE_REGISTRIES = BitDotComResilience.createRegistries();
    }
    return RESILIENCE_REGISTRIES;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification spec = new ExchangeSpecification(this.getClass());
    spec.setSslUri(API_BASE_URL);
    spec.setHost("www.bit.com");
    spec.setPort(80);
    spec.setExchangeName("Bit.com");
    spec.setExchangeDescription("Bit.com Exchange.");
    AuthUtils.setApiAndSecretKey(spec, "bitdotcom");
    return spec;
  }

  @Override
  public void applySpecification(ExchangeSpecification exchangeSpecification) {
    concludeHostParams(exchangeSpecification);
    super.applySpecification(exchangeSpecification);
  }

  public boolean usingSandbox() {
    return enabledSandbox(exchangeSpecification);
  }

  @Override
  public void remoteInit() {

    try {
      BitDotComMarketDataService marketDataService =
          (BitDotComMarketDataService) this.marketDataService;

      BitDotComAccountService accountService = (BitDotComAccountService) getAccountService();
      //      Map<String, AssetDetail> assetDetailMap = null;
      //      if (!usingSandbox() && isAuthenticated()) {
      //        assetDetailMap = accountService.getAssetDetails();
      //      }
      //      if(usingSandbox()){
      //        if(isFuturesSandbox()){
      //          BitDotComAdapters.adaptFutureExchangeMetaData(exchangeMetaData,
      // marketDataService.getFutureExchangeInfo());
      //        } else {
      //          exchangeMetaData =
      // BitDotComAdapters.adaptExchangeMetaData(marketDataService.getExchangeInfo(),
      // assetDetailMap);
      //        }
      //      } else {
      //        exchangeMetaData =
      // BitDotComAdapters.adaptExchangeMetaData(marketDataService.getExchangeInfo(),
      // assetDetailMap);
      //        if(isFuturesEnabled()){
      //          BitDotComAdapters.adaptFutureExchangeMetaData(exchangeMetaData,
      // marketDataService.getFutureExchangeInfo());
      //        }
      //      }

    } catch (Exception e) {
      throw new ExchangeException("Failed to initialize: " + e.getMessage(), e);
    }
  }

  protected boolean isAuthenticated() {
    return exchangeSpecification != null
        && exchangeSpecification.getApiKey() != null
        && exchangeSpecification.getSecretKey() != null;
  }
}
