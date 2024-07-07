package com.msleads.msleads.config;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig {

    public JerseyConfig() {

    }

    @PostConstruct
    public void setup() {
        // packages("com.msleads.msleads.resource");
    }
}
