package logic.model.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.model.Question;

public class QuestionQueries {
	
	private QuestionQueries() {
		 throw new IllegalStateException("Utility class");
	 }
	
	public static void saveQuestion(Statement stmt,Question question,String text,String image,String type) throws SQLException  {
		
		String sql =" INSERT INTO domandaproposta (ID,Titolo,Testo,Immagine,Risolto,Tipo,Studente,Materia) "
				+ "VALUES ("+question.getId()+",'"+question.getTitle()+"','"+text+"','"+image+"',"+question.isSolved()+", "
						+ "'"+type+"','"+question.getStudent().getUsername()+"','"+question.getQuestionSub().getName()+"');";
		
		stmt.executeUpdate(sql);
	}

	public static ResultSet getId(Statement stmt) throws SQLException {
		String sql ="SELECT max(id) AS count FROM domandaproposta ;";
		return stmt.executeQuery(sql);
		
	}
	
	public static ResultSet getQuestions(Statement stmt,String username) throws SQLException{
		String sql ="SELECT * FROM domandaproposta WHERE materia in (SELECT materia FROM segue WHERE studente = '"+username+"') ORDER BY domandaproposta.ID;";
		return stmt.executeQuery(sql);
		
	}
	
}
