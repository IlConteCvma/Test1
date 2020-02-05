package logic.model;

public class Lesson {
	
	private int startHour;
	private int endHour;
	private int dayOfWeek;
	private Room rommLesson;
	private Subject subjectLesson;
	
	public Lesson(int startHour, int endHour, int dayOfWeek) {
		this.startHour = startHour;
		this.endHour = endHour;
		this.dayOfWeek = dayOfWeek;	
	}
}
