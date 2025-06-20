package com.example.VehicleManagementusingJDBC.Config;

import java.sql.*;

public class JDBCUtil  {
    private static final String URL = "jdbc:mysql://localhost:3306/DemoDatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
