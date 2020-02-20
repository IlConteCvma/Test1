package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.model.Room;
import logic.model.SingletonConnectionDB;
import logic.model.queries.RoomQueries;

public class RoomDao {
	
	private RoomDao() {
		throw new IllegalStateException("Utility class");
	}
	

	public static Room getRoomOfLesson(int idLesson) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		Room roomOfLesson;
		try {
		conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();

		// create statement
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// execute query
		ResultSet rs = RoomQueries.findRoomByIdLesson(stmt, idLesson);

		if (!rs.first()) {
			roomOfLesson = null;
		} else {
			rs.first();
			String name = rs.getString("Nome");
			int numRow = rs.getInt("NumRighe");
			int numCol = rs.getInt("NumColonne");
			roomOfLesson = new Room(name, numRow, numCol);
			roomOfLesson.setPlaces(SeatDao.getSeatsOfRoom(name));
		}

		rs.close();
		}finally
	
		{
			if (stmt != null) {
				stmt.close();
			}
		}
		return roomOfLesson;

	}


	public static Room getRoom(String nameRoom) throws SQLException {

		Statement stmt = null;
		Connection conn = null;
		Room roomOfLesson;

		try {
			// create connection
			conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
			if (conn == null) {
				throw new SQLException();
			}
			// create statement
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// execute query
			ResultSet rs = RoomQueries.findInfoRoom(stmt, nameRoom);
			// check if a returned zero value
			if (!rs.first()) {
				roomOfLesson = null;
			} else {
				// returned one value
				rs.first();
				String name = rs.getString("Nome");
				int numRow = rs.getInt("NumRighe");
				int numColumn = rs.getInt("NumColonne");

				roomOfLesson = new Room(name, numRow, numColumn);

			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return roomOfLesson;
	}

}
