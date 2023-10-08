package org.knowm.xchange.bitdotcom;

import static org.knowm.xchange.bitdotcom.BitDotComConstants.HEADER_API_KEY;
import static org.knowm.xchange.bitdotcom.BitDotComConstants.QUERY_PARAM_SIGNATURE;
import static org.knowm.xchange.bitdotcom.BitDotComConstants.QUERY_PARAM_TIMESTAMP;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import org.knowm.xchange.bitdotcom.dto.BitDotComResponse;
import org.knowm.xchange.bitdotcom.dto.SystemTime;
import org.knowm.xchange.bitdotcom.dto.account.BitDotComAccounts;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BitDotCom {

  @GET
  @Path("/spot/v1/system/time")
  BitDotComResponse<SystemTime> systemTime(
  ) throws IOException;
}
