package com.msleads.msleads.config;

import com.msleads.msleads.service.DatabaseConnectionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public DatabaseConnectionService databaseConnectionService() {
        return new DatabaseConnectionService();
    }

    @Bean
    public JerseyConfig jerseyConfig() {
        return new JerseyConfig();
    }
}
