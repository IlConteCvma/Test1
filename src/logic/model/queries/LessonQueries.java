package logic.model.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LessonQueries {
	
	private LessonQueries() {
		 throw new IllegalStateException("Utility class");
	 }
	
	public static ResultSet selectNextLesson(Statement stmt,String username) throws SQLException  {
		
		String sql = "SELECT lezione.ID, min(TIMEDIFF(lezione.OraInizio,TIME(NOW()))) " + 
					 "FROM studente join segue on studente.Username = segue.Studente join lezione on lezione.Materia=segue.Materia "
					 + "WHERE lezione.Giorno = DAYOFWEEK(NOW()) AND studente ='"+ username
					 +"'AND TIMEDIFF(lezione.OraInizio,TIME(NOW())) > 0;";
		
		return stmt.executeQuery(sql);
		
	}
	
	public static ResultSet selectInfoLessonById(Statement stmt, int id) throws SQLException  {
		
		String sql = "SELECT * FROM lezione WHERE lezione.ID ="+ id +";";
		
		return stmt.executeQuery(sql);
		
	}
}
