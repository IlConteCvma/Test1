package logic.controller;

import java.sql.SQLException;

import execption.EntityNotFoundException;
import logic.Session;
import logic.bean.StudentBean;
import logic.model.Student;
import logic.model.dao.StudentDao;


public class LoginController {
		
	public void login(StudentBean potentialStud) throws SQLException, EntityNotFoundException {
		String username = potentialStud.getUsername();
		String password = potentialStud.getPassword();
		Student stud = StudentDao.findStudentLog(username,password);
		
		
		if(Session.getSession().getStudent() == null) {
			Session.getSession().setStudent(stud);
			
		}
		else {
			if(!(Session.getSession().getStudent().getUsername().equals(stud.getUsername()))) {
				Session.getSession().setIndexOfSeat(0);
				Session.getSession().setStudent(stud);
				
			}
		}
		
	}	

	
}
