package logic.view.graphic.controller;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import logic.view.AlertControl;
import logic.view.NamePage;



public class HomeMenuGraphicController extends GraphicController {
	
	private static final String WARNING = "WARNING";
	private static final String NOTAVAILABLE = "Operation not available";
	@FXML
	public void homeButton(ActionEvent e) throws IOException {
		goToPage(NamePage.HOME);
	}
	
	public void calendarButton() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}
	
	public void forumButton() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}
	
	public void profileButton() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}
	
	public void todayLessonButton() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}
	
	public void communicationButton() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}
	
	public void nextLessonButton() {
		AlertControl.infoBox(NOTAVAILABLE, WARNING);
	}
	
	public void logOutButton() throws IOException {
		if(AlertControl.confirmation()) {
			goToPage(NamePage.LOGIN);
		}
	}

	
	
	
}
