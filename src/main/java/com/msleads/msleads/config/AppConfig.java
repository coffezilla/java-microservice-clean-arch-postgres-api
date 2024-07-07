package com.msleads.msleads.config;

import com.msleads.msleads.repository.LeadRepository;
import com.msleads.msleads.repository.UserRepository;
import com.msleads.msleads.service.DatabaseConnectionService;
import com.msleads.msleads.service.JwtService;
import com.msleads.msleads.service.LeadService;
import com.msleads.msleads.service.UserService;
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

    @Bean
    public UserRepository userRepository(DatabaseConnectionService connectionService, JwtService jwtService) {
        return new UserRepository(connectionService, jwtService);
    }


    // service
    @Bean
    public LeadService leadService(LeadRepository leadRepository) {
        return new LeadService(leadRepository);
    }
    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }

    // filter
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    // jersey config
    @Bean
    public JerseyConfig jerseyConfig(LeadService leadService, UserService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        return new JerseyConfig(leadService, userService, jwtAuthenticationFilter);
    }
}