package com.internationalairportmanagementsystem.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "InternationalAirportManagementSystem",
                        email = "phann.ny0917@gmail.com"
                ),
                description = "OpenApi documentation for International Airport Management System",
                title = "OpenApi Specification - AMS",
                version = "1.0",
                license = @License(
                        name = "License name",
                        url = "htpps://api.com"
                ),
                termsOfService = "Terms of Service"
        ),
        servers = {
                @Server(
                        description = "International Airport Management System",
                        url = "http://localhost:8080"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)

@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
