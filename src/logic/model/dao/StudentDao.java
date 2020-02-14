package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logic.model.Address;
import logic.model.SingletonConnectionDB;
import logic.model.Student;
import logic.model.TypeVehicle;
import logic.model.Vehicle;
import logic.model.queries.StudentQueries;

public class StudentDao {

	private StudentDao() {
		throw new IllegalStateException("Utility class");
	}

	public static Student findStudentLog(String possibleUsername, String possiblePassword) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		Student studLog = null;
		ResultSet rs = null;

		try {
			conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
			if (conn == null) {
				throw new SQLException();
			}
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = StudentQueries.selectStudent(stmt, possibleUsername, possiblePassword);
			studLog = buildStudent(rs);
			if (studLog == null) {
				throw new SQLException();
			}

			SingletonConnectionDB.increaseCount();

			rs.close();
		} finally {

			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				SingletonConnectionDB.close();
				SingletonConnectionDB.increaseCount();
			}
		}

		return studLog;
	}

	public static Student findStudent(String username) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		Student stud = null;
		ResultSet rs = null;

		try {
			conn = (SingletonConnectionDB.getSingletonConnection()).getConnection();
			if (conn == null) {
				throw new SQLException();
			}
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = StudentQueries.selectSingleStudent(stmt, username);

			stud = buildStudent(rs);
			if (stud == null) {
				throw new SQLException();
			}

			rs.close();
		} finally {

			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				SingletonConnectionDB.close();
				SingletonConnectionDB.increaseCount();
			}
		}

		return stud;
	}

	private static Student buildStudent(ResultSet rs) throws SQLException {
		if (!rs.first()) {

			return null;
		} else {
			rs.first();

			String name = rs.getString("Nome");
			String surname = rs.getString("Cognome");
			String userna = rs.getString("Username");
			String password = null;
			String address = rs.getString("Indirizzo");
			String streetNumber = rs.getString("Civico");
			String city = rs.getString("Citta");
			String typeVehicle = rs.getString("Veicolo");

			Vehicle vehicleStudent = new Vehicle(TypeVehicle.valueOf(typeVehicle));
			Address addressStudent = new Address(address, streetNumber, city);

			return new Student(name, surname, userna, password, addressStudent, vehicleStudent);

		}

	}

	public static void insertNewStudent(Student newStudent) throws SQLException {

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
			StudentQueries.insertNewStudent(stmt, newStudent);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				SingletonConnectionDB.close();
			}

		}

	}
}