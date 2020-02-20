package logic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnectionDB {

	private static SingletonConnectionDB instance = null;
	private static Connection conn;

	protected SingletonConnectionDB() throws SQLException {
		connect();
	}

	private static void connect() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost/maaldb", "root", "toor");
	}

	public Connection getConnection() {
		return conn;
	}

	public static synchronized SingletonConnectionDB getSingletonConnection() throws SQLException {
		if (SingletonConnectionDB.instance == null)
			SingletonConnectionDB.instance = new SingletonConnectionDB();
		return instance;
	}

}