package logic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnectionDB {
	
	private static SingletonConnectionDB instance = null;
	private static int count = 0;
	private static Connection conn;
	
	
	
	protected SingletonConnectionDB(){
		connect();
	}
	
	private static void connect(){
		try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost/maaldb", "root", "");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn) {
		  try {

	            conn.close();

	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	}
	
	public static void increaseCount() {
		SingletonConnectionDB.count++;
	}
	
	public static void decreaseCount() {
		SingletonConnectionDB.count--;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public static synchronized SingletonConnectionDB getSingletonConnection() {
		count++;
		
		if (SingletonConnectionDB.instance == null)
			SingletonConnectionDB.instance = new SingletonConnectionDB();
		return instance;
	}
	
	public static synchronized void close() {
		if(SingletonConnectionDB.count==0) {
			try {
				SingletonConnectionDB.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}