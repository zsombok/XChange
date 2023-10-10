package org.knowm.xchange.bitdotcom.service;

import static org.knowm.xchange.utils.DigestUtils.bytesToHex;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import org.knowm.xchange.bitdotcom.BitDotComConstants;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

public class BitDotComDigest extends BaseParamsDigest {

  private BitDotComDigest(String secretKeyBase64) {
    super(secretKeyBase64, HMAC_SHA_256);
  }

  public static BitDotComDigest createInstance(String secretKeyBase64) {
    return secretKeyBase64 == null ? null : new BitDotComDigest(secretKeyBase64);
  }

  @SuppressWarnings({"unchecked"})
  private static String toParamString(Object value) {
    TreeMap<String, Object> sorted;
    if (value instanceof Map) {
      sorted = new TreeMap<>((Map<String, Object>) value);
    } else if (value instanceof List) {
      StringBuilder arrValue = new StringBuilder("[");
      for (Object obj : (List<?>) value) {
        arrValue.append(toParamString(obj)).append("&");
      }
      arrValue = new StringBuilder(arrValue.substring(0, arrValue.length() - 1));
      arrValue.append("]");
      return arrValue.toString();
    } else {
      return String.valueOf(value);
    }
    StringBuilder paramString = new StringBuilder();
    for (Map.Entry<String, Object> entry : sorted.entrySet()) {
      String key = entry.getKey();
      Object val = entry.getValue();
      paramString.append(key).append("=").append(toParamString(val)).append("&");
    }
    return paramString.substring(0, paramString.length() - 1);
  }

  private static String processRequestBody(String jsonString) {
    ObjectMapper oM = new ObjectMapper();
    Map<String, Object> map;
    try {
      map = oM.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return toParamString(map);
  }

  private static String processQueryParameters(String queryString) {
    Map<String, String> sortedParams = new TreeMap<>();
    Arrays.stream(queryString.split("&"))
        .forEach(
            queryParamPair -> {
              String[] pair = queryParamPair.split("=");
              sortedParams.computeIfAbsent(pair[0], k -> pair[1]);
            });
    return sortedParams.entrySet().stream()
        .filter(e -> !BitDotComConstants.QUERY_PARAM_SIGNATURE.equals(e.getKey()))
        .map(e -> e.getKey() + "=" + e.getValue())
        .collect(Collectors.joining("&"));
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {
    final String plainText;

    switch (restInvocation.getHttpMethod()) {
      case "GET":
        plainText = processQueryParameters(restInvocation.getQueryString());
        break;
      case "POST":
        plainText = processRequestBody(restInvocation.getRequestBody());
        break;
      default:
        throw new RuntimeException("Not support http method: " + restInvocation.getHttpMethod());
    }

    String stringToSign = restInvocation.getPath() + "&" + plainText;
    Mac mac = getMac();
    return bytesToHex(mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8)));
  }
}

// Python example
//  def encode_list(self, item_list):
//    list_val = []
//    for item in item_list:
//    obj_val = self.encode_object(item)
//    list_val.append(obj_val)
//    output = '&'.join(list_val)
//    output = '[' + output + ']'
//    return output
//
//    def encode_object(self, obj):
//    if isinstance(obj, (str, int)):
//    return obj
//
//    # treat obj as dict
//    sorted_keys = sorted(obj.keys())
//    ret_list = []
//    for key in sorted_keys:
//    val = obj[key]
//    if isinstance(val, list):
//    list_val = self.encode_list(val)
//    ret_list.append(f'{key}={list_val}')
//    elif isinstance(val, dict):
//    # call encode_object recursively
//    dict_val = self.encode_object(val)
//    ret_list.append(f'{key}={dict_val}')
//    elif isinstance(val, bool):
//    bool_val = str(val).lower()
//    ret_list.append(f'{key}={bool_val}')
//    else:
//    general_val = str(val)
//    ret_list.append(f'{key}={general_val}')
//
//    sorted_list = sorted(ret_list)
//    output = '&'.join(sorted_list)
//    return output
//
//    def get_signature(self, http_method, api_path, param_map):
//    str_to_sign = api_path + '&' + self.encode_object(param_map)
//    print('str_to_sign = ' + str_to_sign)
//    sig = hmac.new(self.secret_key.encode('utf-8'), str_to_sign.encode('utf-8'),
// digestmod=hashlib.sha256).hexdigest()
//    return sig
