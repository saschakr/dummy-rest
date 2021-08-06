package com.gitlab.saschakr.dummyrest.api;

import com.gitlab.saschakr.dummyrest.service.ResetService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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
