package logic;


import logic.model.Student;


public class Session {
	private static Session instance = null;
	private Student studentLog;
	private int indexOfSeat;
	
	protected Session(){
		indexOfSeat = 0;
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

	public int getIndexOfSeat() {
		return indexOfSeat;
	}

	public void setIndexOfSeat(int indexOfSeat) {
		this.indexOfSeat = indexOfSeat;
	}

}
