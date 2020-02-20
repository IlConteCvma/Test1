package logic.model.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.model.Student;

public class StudentQueries {
	
	private StudentQueries() {
		 throw new IllegalStateException("Utility class");
	 }

	public static ResultSet selectStudent(Statement stmt, String username, String password) throws SQLException  {
		String sql = "SELECT * FROM studente where Username = '" + username + "'and Password = '"+password+"';";
		return stmt.executeQuery(sql);
	}
	
	public static ResultSet selectSingleStudent(Statement stmt, String username ) throws SQLException{
		String sql = "SELECT * FROM studente where Username = '" + username + "' ;";
		return stmt.executeQuery(sql);
	}
	
	public static int insertNewStudent(Statement stmt, Student newStudent ) throws SQLException{
		String sql = "		INSERT INTO `studente` (`Nome`, `Cognome`, `Username`, `Password`, `Indirizzo`, `Veicolo`, `Civico`, `Citta`) "
				+ "VALUES ('"+ newStudent.getName() +"', '"+ newStudent.getSurname() +"', '"+ newStudent.getUsername() +"', '"+ newStudent.getPassword() +"', "
				+ "'"+ newStudent.getAddress().getStreet() +"', '"+ newStudent.getVehicle().getType().toString() +"', '"+ newStudent.getAddress().getStreetNumber() +"', '"+ newStudent.getAddress().getCity() +"');";
		
		return stmt.executeUpdate(sql);
	}
		
}

