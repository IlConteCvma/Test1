package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.Seat;
import logic.model.SingletonConnectionDB;
import logic.model.queries.SeatQueries;

public class SeatDao {
	
	private SeatDao() {
		throw new IllegalStateException("Utility class");
	}
	
	public static List<Seat> getSeatsOfRoom(String nameRoom) throws SQLException {
        
	 	Statement stmt = null;
        Connection conn = null;
        List<Seat> seatsOfRoom;
        
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            ResultSet rs = SeatQueries.findSeatsOfRoom(stmt, nameRoom);
            //check if a returned zero value
            if (!rs.first()){
            	seatsOfRoom = null;
            }else {
            	//returned one value
            	rs.first();
            	seatsOfRoom = new ArrayList<>();
            	do{
            		int idSeat = rs.getInt("ID");
            		boolean busy = rs.getBoolean("Busy");
                    Seat place = new Seat(idSeat, busy);
                    
                    seatsOfRoom.add(place);

                }while(rs.next());
            }
            rs.close();
            } finally {     
            	if(stmt != null){
            		stmt.close();
            	}
            	
            }
        	return seatsOfRoom;
    }
	
	public static void occupySeat(String nameRoom, int idSeat) throws SQLException {
        
	 	Statement stmt = null;
        Connection conn = null;
        
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            SeatQueries.occupySeat(stmt, nameRoom, idSeat);
            } finally {     
            	if(stmt != null){
            		stmt.close();
            	}
            }
      }
	
	public static void freeSeat(String nameRoom, int idSeat) throws SQLException {
        
	 	Statement stmt = null;
        Connection conn = null;
        
        try {
        	//create connection
        	conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
        	if (conn== null) {
				throw new SQLException();
			}
        	//create statement
        	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	//execute query
            SeatQueries.freeSeat(stmt, nameRoom, idSeat);
            } finally {     
            	if(stmt != null){
            		stmt.close();
            	}
            }
      }
}
