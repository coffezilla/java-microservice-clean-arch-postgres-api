package com.msleads.msleads.repository;

import com.msleads.msleads.model.Lead;
import com.msleads.msleads.service.DatabaseConnectionService;

import java.sql.*;

public class LeadRepository {
    private static final String INSERT_LEAD = "INSERT INTO leads (name, email, created_at) VALUES (?,?,?)";
    private static final String SELECT_ALL_LEADS = "SELECT id, name, email, created_at FROM leads";

    private final DatabaseConnectionService connectionService;

    // constructor
    public LeadRepository (DatabaseConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public Lead createLead(Lead lead) {
        try(Connection connection = connectionService.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_LEAD)) {
            statement.setString(1, lead.getName());
            statement.setString(2,lead.getEmail());
            statement.setTimestamp(3, Timestamp.valueOf(lead.getCreatedAt()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
