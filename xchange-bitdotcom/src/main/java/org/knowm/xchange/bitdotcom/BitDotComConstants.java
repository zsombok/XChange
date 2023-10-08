package org.knowm.xchange.bitdotcom;

public class BitDotComConstants {

  public static final String HTTPS = "https://";
  public static final String API_BASE_HOST = "api.bit.com";
  public static final String API_BASE_URL = HTTPS + API_BASE_HOST;
  public static final String SANDBOX_BASE_HOST = "betaapi.bitexch.dev";
  public static final String SANDBOX_BASE_URL = HTTPS + SANDBOX_BASE_HOST;

  public static final String SPECIFIC_PARAM_USE_SANDBOX = "Use_Sandbox";

  public static final String HEADER_API_KEY = "X-Bit-Access-Key";

  public static final String QUERY_PARAM_TIMESTAMP = "timestamp";
  public static final String QUERY_PARAM_SIGNATURE = "signature";
}
