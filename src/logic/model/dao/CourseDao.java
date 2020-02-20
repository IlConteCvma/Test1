package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.model.Course;
import logic.model.SingletonConnectionDB;
import logic.model.Subject;
import logic.model.queries.CourseQueries;

public class CourseDao {

	private CourseDao() {
		throw new IllegalStateException("Utility class");
	}

	public static Course getCourse(String courseName) throws SQLException {

		Statement stmt = null;
		Connection conn = null;
		Course courseSearched = null;

		List<Subject> subject;
		try {
			// create connection
			conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
			if (conn == null) {
				throw new SQLException();
			}
			// create statement
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// execute query
			ResultSet rs = CourseQueries.findCourse(stmt, courseName);

			if (!rs.first()) {
				subject = null;
			} else {
				// returned one value
				rs.first();
				subject = new ArrayList<>();
				do {

					String name = rs.getString("Nome");
					String abbreviation = rs.getString("Sigla");
					double index = rs.getDouble("Indice");

					Subject sub = new Subject(name, abbreviation, index);

					subject.add(sub);

				} while (rs.next());
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		if (subject != null) {
			courseSearched = new Course(courseName, subject);
		}

		return courseSearched;

	}

}
