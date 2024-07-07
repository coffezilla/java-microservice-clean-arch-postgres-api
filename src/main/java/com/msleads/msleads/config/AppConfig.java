package com.msleads.msleads.config;

import com.msleads.msleads.model.Lead;
import com.msleads.msleads.repository.LeadRepository;
import com.msleads.msleads.service.DatabaseConnectionService;
import com.msleads.msleads.service.LeadService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public DatabaseConnectionService databaseConnectionService() {
        return new DatabaseConnectionService();
    }

    // repository
    @Bean
    public LeadRepository leadRepository(DatabaseConnectionService connectionService) {
        return new LeadRepository(connectionService);
    }

    // service
    @Bean
    public LeadService leadService(LeadRepository leadRepository) {
        return new LeadService(leadRepository);
    }

    //
    @Bean
    public JerseyConfig jerseyConfig(LeadService leadService) {
        return new JerseyConfig(leadService);
    }
}
