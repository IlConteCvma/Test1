package logic.model;

import java.sql.Time;

public class Lesson {
	
	private Time startHour;
	private Time endHour;
	private int dayOfWeek;
	private Room roomLesson;
	private Subject subjectLesson;
	
	public Lesson(Time startHour, Time endHour, int dayOfWeek) {
		this.startHour = startHour;
		this.endHour = endHour;
		this.dayOfWeek = dayOfWeek;	
	}
	
	public Lesson(Time startHour, Time endHour, int dayOfWeek, Room roomLesson, Subject subjectLesson) {
		this.startHour = startHour;
		this.endHour = endHour;
		this.dayOfWeek = dayOfWeek;	
		this.roomLesson = roomLesson;
		this.subjectLesson = subjectLesson;
	}
	
	public Time getStartHour() {
		return startHour;
	}
	
	public Time getEndHour() {
		return endHour;
	}
	
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	
	public void setRoomLesson(Room room) {
		this.roomLesson = room;
	}
	
	public void setSubjectLesson(Subject subject) {
		this.subjectLesson = subject;
	}
	
	public Room getRoomLesson() {
		return this.roomLesson;
	}
	
	public Subject getSubjectLesson() {
		return subjectLesson;
	}
}
