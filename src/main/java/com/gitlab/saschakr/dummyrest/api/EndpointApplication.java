package com.gitlab.saschakr.dummyrest.api;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.servers.ServerVariable;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Java EE Dummy REST",
                description = "Dummy REST-API for testing and learning",
                version = "1.0",
                contact = @Contact(name = "SaschaKr", url = "https://gitlab.com/SaschaKr")
        ),
        servers = @Server(
                url = "http://{host}:{port}/",
                description = "dummy server",
                variables = {
                        @ServerVariable(name = "host", defaultValue = "localhost"),
                        @ServerVariable(name = "port", defaultValue = "8080")
                })
)
@ApplicationPath("/api")
public class EndpointApplication extends Application {
}