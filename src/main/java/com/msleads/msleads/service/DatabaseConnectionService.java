package com.msleads.msleads.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
    private static final String DB_URL = "jdbc:postgresql://dpg-cq5l0kuehbks73bqfs80-a.oregon-postgres.render.com:5432/mypostgres_mrkr";
    private static final String DB_USERNAME = "admin";
    private static final String DB_PASSWORD = "9kw0FT61QuWRsVpVq0v0XIj23WkHJCPE";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}