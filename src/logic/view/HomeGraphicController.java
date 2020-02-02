package logic.view;


import javafx.fxml.*;
import javafx.scene.input.MouseEvent;


public class HomeGraphicController {

	
	@FXML
	public void homeButton(MouseEvent e) {
		System.out.println("HOME");
	}
	
	public void calendarButton(MouseEvent e) {
		System.out.println("CALENDAR");
	}
	
	public void forumButton(MouseEvent e) {
		System.out.println("FORUM");
	}
	
	public void profileButton(MouseEvent e) {
		System.out.println("PROFILE");
	}
	
	public void todayLessonButton(MouseEvent e) {
		System.out.println("LESSON");
	}
	
	public void communicationButton(MouseEvent e) {
		System.out.println("COMMUNICATION");
	}
	
	public void nextLessonButton(MouseEvent e) {
		System.out.println("NEXT Lesson");
	}
	
	public void logOutButton(MouseEvent e) {
		System.out.println("LOGOUT");
	}
	
	
	
	
}
