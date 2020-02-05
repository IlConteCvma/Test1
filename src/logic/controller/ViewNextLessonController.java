package logic.controller;

import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.Vector;

import logic.model.Lesson;

public class ViewNextLessonController {
	private Lesson nextLesson;
	private LocalTime date;
	
	public Lesson getNextLesson() {
		return nextLesson;
	}
	
	public Vector<Integer> getActualHour() {
		GregorianCalendar dataAttuale=new GregorianCalendar();
		int ore = dataAttuale.get(GregorianCalendar.HOUR);
		int minuti = dataAttuale.get(GregorianCalendar.MINUTE);
		int secondi = dataAttuale.get(GregorianCalendar.SECOND);
		Vector<Integer> actualHour = new Vector<>();
		actualHour.add(ore);
		actualHour.add(minuti);
		actualHour.add(secondi);
		return actualHour;
	}
}
