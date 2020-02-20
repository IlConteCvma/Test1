package logic.model.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomQueries {
	
	private RoomQueries() {
		 throw new IllegalStateException("Utility class");
	 }
	
	public static ResultSet findRoomByIdLesson(Statement stmt, int idLesson) throws SQLException  {
		
		
		String sql = "SELECT * " + 
				"FROM lezione join aula on lezione.Aula = aula.Nome " + 
				"WHERE lezione.ID = "+ idLesson +";";
		
		return stmt.executeQuery(sql);
		
	}
	
	public static ResultSet findInfoRoom(Statement stmt, String name) throws SQLException  {
		
		String sql = "SELECT * " + 
				"FROM aula " + 
				"WHERE Nome = '"+ name +"';";
		
		return stmt.executeQuery(sql);
		
	}
}
