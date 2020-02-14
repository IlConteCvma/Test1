package logic;


import logic.model.Student;


public class Session {
	private static Session instance = null;
	private Student studentLog;

	
	protected Session(){
		
	}
	
	public static synchronized Session getSession() {
		if (Session.instance == null) {
			Session.instance = new Session();
			}
		return instance;
	}

	public Student getStudent() {
		return this.studentLog;
	}

	public void setStudent(Student student) {
		this.studentLog = student;
	}

}
