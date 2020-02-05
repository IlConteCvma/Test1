package logic.controller;

import java.sql.SQLException;

import logic.model.Student;
import logic.model.Dao.StudentDao;

public class LoginController {
		
	public boolean login(String username, String password) throws SQLException {
		boolean found = false;
		Student stud = StudentDao.findStudentLog(username, password);
		
		if(stud != null) {
			found = true;
		}
		
		return found;
	}
	
}
