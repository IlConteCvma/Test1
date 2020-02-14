package logic.bean;

import java.util.List;

import logic.model.Student;
import logic.model.Subject;

public class CourseBean {

	private String nameOfCourse;
	private List<Subject> subjectOfCourse;
	private List<Student> studentOfCourse;
	
	public String getNameOfCourse() {
		return nameOfCourse;
	}
	
	public void setNameOfCourse(String nameOfCourse) {
		this.nameOfCourse = nameOfCourse;
	}
	
	public List<Subject> getSubjectOfCourse() {
		return subjectOfCourse;
	}
	
	public void setSubjectOfCourse(List<Subject> subjectOfCourse) {
		this.subjectOfCourse = subjectOfCourse;
	}
	
	public List<Student> getStudentOfCourse() {
		return studentOfCourse;
	}
	
	public void setStudentOfCourse(List<Student> studentOfCourse) {
		this.studentOfCourse = studentOfCourse;
	}
	
}
