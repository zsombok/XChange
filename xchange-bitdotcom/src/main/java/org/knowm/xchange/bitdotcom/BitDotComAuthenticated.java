package org.knowm.xchange.bitdotcom;

import static org.knowm.xchange.bitdotcom.BitDotComConstants.HEADER_API_KEY;
import static org.knowm.xchange.bitdotcom.BitDotComConstants.QUERY_PARAM_SIGNATURE;
import static org.knowm.xchange.bitdotcom.BitDotComConstants.QUERY_PARAM_TIMESTAMP;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.QueryParam;
import org.knowm.xchange.bitdotcom.dto.BitDotComResponse;
import org.knowm.xchange.bitdotcom.dto.account.BitDotComAccounts;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BitDotComAuthenticated {

  @GET
  @Path("/um/v1/accounts")
  BitDotComResponse<BitDotComAccounts> umAccounts(
    @HeaderParam(HEADER_API_KEY) String apiKey,
    @QueryParam(QUERY_PARAM_TIMESTAMP) SynchronizedValueFactory<Long> timestamp,
    @QueryParam(QUERY_PARAM_SIGNATURE) ParamsDigest signature,
    @QueryParam("with_linear_pair_margins") String withLinearPairMargins // "true" to include
  ) throws IOException;
}
