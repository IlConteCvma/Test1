package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.model.Room;
import logic.model.SingletonConnectionDB;
import logic.model.queries.RoomQueries;

public class RoomDao {
	
	public String getInfoRoomByIdLesson(int idLesson) throws SQLException {
        
	 	Statement stmt = null;
        Connection conn = null;
        String nameRoomOfLesson;
        
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            ResultSet rs = RoomQueries.findNameRoomByIdLesson(stmt, idLesson);
            //check if a returned zero value
            if (!rs.first()){
            	nameRoomOfLesson = null;
            }else {
            	//returned one value
            	rs.first();
            	
            	nameRoomOfLesson = rs.getString("Nome");
            }
            rs.close();
            } finally {     
            	if(stmt != null){
            		stmt.close();
            	}
            	if (conn != null) {
					SingletonConnectionDB.close();
				}
            }
        	return nameRoomOfLesson;
	}
	
	public Room getRoom(String nameRoom)  throws SQLException {
        
	 	Statement stmt = null;
        Connection conn = null;
        Room roomOfLesson;
        
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            ResultSet rs = RoomQueries.findInfoRoom(stmt, nameRoom);
            //check if a returned zero value
            if (!rs.first()){
            	roomOfLesson = null;
            }else {
            	//returned one value
            	rs.first();
            	String name = rs.getString("Nome");
            	int numRow = rs.getInt("NumRighe");
            	int numColumn = rs.getInt("NumColonne");
            	
            	roomOfLesson = new Room(name, numRow, numColumn);
            	
            }
            rs.close();
            } finally {     
            	if(stmt != null){
            		stmt.close();
            	}
            	if (conn != null) {
    				SingletonConnectionDB.close();
    			}
            }
        	return roomOfLesson;
    }
	
}
