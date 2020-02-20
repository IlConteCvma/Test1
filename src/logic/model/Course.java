package logic.model;

import java.util.List;

public class Course {
	
	private String nameOfCourse;
	private List<Subject> subjectOfCourse;
	private List<Student> studentOfCourse;
	
	public Course(String nameOfCourse, List<Subject> subjectOfCourse) {
		this.nameOfCourse = nameOfCourse;
		this.subjectOfCourse = subjectOfCourse;
	}
	
	public Course(String nameOfCourse, List<Subject> subjectOfCourse, List<Student> studentOfCourse) {
		this.nameOfCourse = nameOfCourse;
		this.subjectOfCourse = subjectOfCourse;
		this.studentOfCourse = studentOfCourse;
	}
	
	public String getNameOfCourse() {
		return this.nameOfCourse;
	}
	
	public List<Subject> getSubjectOfCourse(){
		return this.subjectOfCourse;
	}
	
	public List<Student> getStudentOfCourse(){
		return this.studentOfCourse;
	}
}
