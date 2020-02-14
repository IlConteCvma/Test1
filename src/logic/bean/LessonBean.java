package logic.bean;

import logic.model.Room;
import logic.model.Subject;

public class LessonBean {
	private int startHour;
	private int endHour;
	private int dayOfWeek;
	private Room roomLesson;
	private Subject subjectLesson;
	
	public int getStartHour() {
		return this.startHour;
	}
	
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getEndHour() {
		return this.endHour;
	}
	
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	
	public int getDayOfWeek() {
		return this.dayOfWeek;
	}
	
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public Room getRoomLesson() {
		return this.roomLesson;
	}
	
	public void setRoomLesson(Room roomLesson) {
		this.roomLesson = roomLesson;
	}
	
	public Subject getSubjectLesson() {
		return this.subjectLesson;
	}
	
	public void setSubjectLesson(Subject subjectLesson) {
		this.subjectLesson = subjectLesson;
	}
}
