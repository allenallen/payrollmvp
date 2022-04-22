package com.tamaraw.payroll.utils;

import com.tamaraw.payroll.exceptions.DBException;
import com.tamaraw.payroll.models.BaseDBObject;

import java.sql.*;

public class DBUtil {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static Connection conn = null;
    private static final String CONN_STR = "jdbc:h2:~/payrollDB";

    public static ResultSet dbExecuteQuery(String query) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connectDb();
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (DBException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void dbExecuteUpdate(String query) {
        Statement statement = null;

        try {
            connectDb();
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (DBException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                disconnectDb();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void connectDb() throws DBException, SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                conn = DriverManager.getConnection(CONN_STR);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBException("Error initializing DB");
            }
        }
    }

    public static void disconnectDb() throws DBException {
        try {
            if (!conn.isClosed() && conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Unable to disconnect DB");
        }
    }
}
