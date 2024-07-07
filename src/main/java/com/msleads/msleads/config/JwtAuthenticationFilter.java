package com.msleads.msleads.config;

import com.msleads.msleads.service.JwtService;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Component
public class JwtAuthenticationFilter implements ContainerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method resourceMethod = resourceInfo.getResourceMethod();

        // Check if the resource method is annotated with @Secured
        if (resourceMethod.isAnnotationPresent(Secured.class)) {
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                abortWithUnauthorized(requestContext);
                return;
            }

            String token = authorizationHeader.substring("Bearer ".length());

            if (!jwtService.verifyJwtToken(token)) {
                abortWithUnauthorized(requestContext);
            }
        }
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("Invalid or missing JWT token")
                .build());
    }
}