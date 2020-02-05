package logic.model;

public class Course {
	
	private String nameOfCourse;
	private Subject[] subjectOfCourse;
	private Student[] studentOfCourse;
	
	public Course(String nameOfCourse) {
		this.nameOfCourse = nameOfCourse;
	}
}
