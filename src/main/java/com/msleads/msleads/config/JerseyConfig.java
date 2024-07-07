package com.msleads.msleads.config;

import com.msleads.msleads.resource.LeadResource;
import com.msleads.msleads.service.LeadService;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(LeadService leadService) {
        register(new LeadResource(leadService));

    }

    @PostConstruct
    public void setup() {
        packages("com.msleads.msleads.resource");
    }
}
