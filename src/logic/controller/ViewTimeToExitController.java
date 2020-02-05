package logic.controller;

import java.time.ZonedDateTime;
import java.util.Vector;

import logic.model.Seat;
import logic.model.Student;

public class ViewTimeToExitController {

	private ViewNextLessonController nextLesson = new ViewNextLessonController();
	private Student studLogged;
	
	public void getInfoByMaps() {
		//in lavorazione..
	}
	
	public void getInfoByMeteo() {
		//in lavorazione..
	}
	
	public void estimateTimeToExit() {
		Vector<Integer> actualHour = new Vector<>();
		actualHour = nextLesson.getActualHour();
		System.out.println("L'ora esatta è " + actualHour.elementAt(0) + ":" + actualHour.elementAt(1) + ":" + actualHour.elementAt(2)); 
	}
	
	public void estimateOccupationRoom() {
		//in lavorazione..
	}
	
	public void occupateSeat(Seat seatToOccupy) {
		seatToOccupy.occupateSeat();
	}
}