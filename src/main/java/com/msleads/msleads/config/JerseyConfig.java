package com.msleads.msleads.config;

import com.msleads.msleads.resource.LeadResource;
import com.msleads.msleads.resource.UserResource;
import com.msleads.msleads.service.LeadService;
import com.msleads.msleads.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public JerseyConfig(LeadService leadService, UserService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        register(new LeadResource(leadService));
        register(new UserResource(userService));
        register(jwtAuthenticationFilter);

    }

    @PostConstruct
    public void setup() {
        packages("com.msleads.msleads.resource");
    }
}
