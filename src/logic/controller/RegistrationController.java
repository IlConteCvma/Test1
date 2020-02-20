package logic.controller;

import java.sql.SQLException;
import java.util.List;

import logic.Session;
import logic.bean.CourseBean;
import logic.bean.UserBean;
import logic.model.Address;
import logic.model.Course;
import logic.model.Student;
import logic.model.Vehicle;
import logic.model.dao.CourseDao;
import logic.model.dao.LessonStudentDao;
import logic.model.dao.StudentDao;

public class RegistrationController {
	
	public void createStudent(UserBean newUser) throws SQLException {
		Address addressUser = new Address(newUser.getAddress(),newUser.getNumberOfStreet(),newUser.getCity());
		Vehicle vehicleUser = new Vehicle(newUser.getVehicle());
		Student newStudent = new Student(newUser.getName(),newUser.getSurname(),newUser.getUsername(),newUser.getPassword(),addressUser,vehicleUser);
		
		StudentDao.insertNewStudent(newStudent);
		Session.getSession().setStudent(newStudent);
		
	}
	
	public CourseBean findCourse(String course) throws SQLException {
		Course courseReturned = CourseDao.getCourse(course);

		//Popule bean
		CourseBean courseBeanSearched = new CourseBean();
		courseBeanSearched.setNameOfCourse(courseReturned.getNameOfCourse());
		courseBeanSearched.setSubjectOfCourse(courseReturned.getSubjectOfCourse());
		
		return courseBeanSearched;
	}
	
	public void followSubject(List<String> subjectsFollow) throws SQLException {
		LessonStudentDao.followMoreSubject(Session.getSession().getStudent().getUsername(), subjectsFollow);
	}
	
}
