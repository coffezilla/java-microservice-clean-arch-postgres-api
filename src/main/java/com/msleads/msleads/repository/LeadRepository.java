package com.msleads.msleads.repository;

import com.msleads.msleads.model.Lead;
import com.msleads.msleads.service.DatabaseConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeadRepository {
    private static final String INSERT_LEAD = "INSERT INTO leads (name, email, created_at) VALUES (?,?,?)";
    private static final String SELECT_ALL_LEADS = "SELECT id, name, email, created_at FROM leads ORDER BY id";
    private static final String SELECT_LEAD_BY_ID = "SELECT * FROM leads WHERE id = ?";
    private static final String DELETE_LEAD = "DELETE FROM leads WHERE id = ?";
    private static final String UPDATE_LEAD = "UPDATE leads SET name = ? WHERE id = ?";

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

    public List<Lead> getAllLeads() {
        List<Lead> leads = new ArrayList<>();

        try(Connection connection = connectionService.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_LEADS);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Lead lead = new Lead();
                lead.setId(resultSet.getLong("id"));
                lead.setName(resultSet.getString("name"));
                lead.setEmail(resultSet.getString("email"));
                leads.add(lead);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leads;
    }

    public void deleteLead(Long leadId) {
        try(Connection connection = connectionService.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_LEAD)) {
            statement.setLong(1, leadId);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Lead findLeadById(Long leadId) {
        try(Connection connection = connectionService.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_LEAD_BY_ID)) {
            statement.setLong(1, leadId);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    Lead lead = new Lead();
                    lead.setId(resultSet.getLong("id"));
                    lead.setName(resultSet.getString("name"));
                    lead.setEmail(resultSet.getString("email"));
                    lead.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                    return lead;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateLead(Lead lead) {
        try(Connection connection = connectionService.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_LEAD)) {
            statement.setString(1,  lead.getName());
            statement.setLong(2, lead.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


}
