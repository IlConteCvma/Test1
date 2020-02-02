package logic.controller;

import logic.model.Student;
import logic.model.StudentDao;

public class LoginController {
		
	public void login(String username, String password) throws Exception {
		Student stud = StudentDao.findStudentLog(username, password);
	}
	
}
