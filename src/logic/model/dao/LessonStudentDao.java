package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import logic.model.SingletonConnectionDB;
import logic.model.queries.LessonStudentQueries;

public class LessonStudentDao {

	private LessonStudentDao() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void followMoreSubject(String username, List<String> subjectsFollow) throws SQLException{

			Statement stmt = null;
			Connection conn = null;
			try {
				// create connection
				conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
				if (conn == null) {
					throw new SQLException();
				}
				// create statement
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// execute query
				for(int i=0;i<subjectsFollow.size();i++) {
					LessonStudentQueries.followNewSubject(stmt, username, subjectsFollow.get(i));
				}
			} finally {
				if (stmt != null) {
					stmt.close();
				}
			}

		
	}
}
