package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String URL = "jdbc:mysql://localhost:3306/db_joaopaulo" +
            "?createDatabaseIfNotExist=true";
    private final String USER = "root";
    private final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
