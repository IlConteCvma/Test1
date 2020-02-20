package logic.model.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SubjectQueries {

	private static final String SELECTALL = "SELECT *";
	
	private SubjectQueries() {
		 throw new IllegalStateException("Utility class");
	 }
	
	public static ResultSet findSubjectOfLesson(Statement stmt, int idLesson) throws SQLException  {
		
		String sql = SELECTALL + 
				"FROM lezione join materia on lezione.Materia=materia.Nome " + 
				"WHERE lezione.ID = "+ idLesson +";";
		
		return stmt.executeQuery(sql);
		
	}
	
	public static ResultSet findSubjectOfStudent(Statement stmt,String username) throws SQLException {
		String sql = SELECTALL + 
				"FROM segue join materia on segue.Materia = materia.Nome " + 
				"WHERE Studente = '"+ username +"';";
	
		
		return stmt.executeQuery(sql);
	}
	
	public static ResultSet findSubjectByName(Statement stmt,String name) throws SQLException{
		String sql = SELECTALL + 
				"FROM materia " + 
				"WHERE Nome = '"+ name +"';";
	
		
		return stmt.executeQuery(sql);
		
	}
	
}
