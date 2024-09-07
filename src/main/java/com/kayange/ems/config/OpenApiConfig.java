package com.kayange.ems.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Ayubu Kayange",
                        email = "kayangejr3@gmail.com",
                        url = "https://kayange-pro.vercel.app"
                ),
                description = "Open API documentation for Employees Management System",
                title = "Employees Management System",
                version = "1.0.0"
        ),
        servers ={
                @Server(
                        description = "Local Server",
                        url = "http://localhost:8080/api"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "basicAuth"
                )
        }
)
@SecurityScheme(
        name = "basicAuth",
        description = "Basic Authentication",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
