package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlConnection {

    private static Connection con = null;

    public static Connection getConnectionMysql() {
        if (con == null) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish the connection
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmvc", "root", "");
                System.out.println("Connection established successfully.");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error connecting to the database.");
                e.printStackTrace();
            }
        }
        return con;
    }

    // Method to close the connection
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing the connection.");
                e.printStackTrace();
            }
        }
    }
}
