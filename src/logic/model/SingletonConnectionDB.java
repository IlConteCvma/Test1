package logic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnectionDB {
	
	private static SingletonConnectionDB instance = null;
	private static Connection conn;
	public static Student studLog;
	
	
	protected SingletonConnectionDB() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/maaldb", "root", "");
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public synchronized static SingletonConnectionDB getSingletonConnection() {
		try {
			if (SingletonConnectionDB.instance == null)
				SingletonConnectionDB.instance = new SingletonConnectionDB();
		}catch (SQLException se) {
            se.printStackTrace();
        }catch (ClassNotFoundException se2) {
        	se2.printStackTrace();
        }
		return instance;
	}
	
	public static Student getStudent() {
		return studLog;
	}
	
	
}