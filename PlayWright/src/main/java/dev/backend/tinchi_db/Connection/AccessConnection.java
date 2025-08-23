package dev.backend.tinchi_db.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:ucanaccess://lib/baitoantinchi.accdb";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
