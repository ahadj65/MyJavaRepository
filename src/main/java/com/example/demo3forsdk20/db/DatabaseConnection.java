package com.example.demo3forsdk20.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private String connectionDriver;
    private String connectionUrl;
    private String connectionUserName;
    private String connectionPassword;

    public Connection connection;

    private DatabaseConnection() {

    }

    private static DatabaseConnection databaseConnection = null;

    public Connection getConnection() {
        connectionDriver = "oracle.jdbc.OracleDriver";
        connectionUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
        connectionUserName = "system";
        connectionPassword = "851218055";

        try {
            Class.forName(connectionDriver);
            connection = DriverManager.getConnection(connectionUrl, connectionUserName, connectionPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    public static synchronized DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

}

