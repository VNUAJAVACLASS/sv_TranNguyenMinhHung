package dao;

import java.sql.Connection;
import java.sql.SQLException;

import db.ConnectDB;

public class BookDAO {
	private Connection getConnection() throws SQLException {
	    return ConnectDB.getConnection();
	}

}
