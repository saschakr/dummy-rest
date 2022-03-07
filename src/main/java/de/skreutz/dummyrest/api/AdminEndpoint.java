package de.skreutz.dummyrest.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.skreutz.dummyrest.service.ResetService;


@Tag(name = "Admin", description = "Admin requests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/admin")
@Metered(name = "AdminEndpoint")
public class AdminEndpoint {

  @Inject
  private ResetService resetService;

  @Inject
  @ConfigProperty(name = "WITH_ADMIN", defaultValue = "false")
  private boolean withAdmin;

  @DELETE
  public Response deleteState() {
    if (this.withAdmin) {
      this.resetService.resetState();
      return Response.ok().build();
    }
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }
}
