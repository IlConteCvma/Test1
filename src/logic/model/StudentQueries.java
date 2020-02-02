package logic.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentQueries {

	public static ResultSet selectStudent(Statement stmt, String username, String password) throws SQLException  {
		String sql = "SELECT * FROM Studente where Username = '" + username + "'and Password = '"+password+"';";
		return stmt.executeQuery(sql);
	}
		
}

