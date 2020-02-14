package logic.view.graphic.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logic.view.AlertControl;
import logic.view.NamePage;


public class NavbarGraphicController extends GraphicController {
	
	private HomeMenuGraphicController controlHome = new HomeMenuGraphicController();
	
	@FXML
	public void homeButton(ActionEvent e) throws IOException {
		controlHome.homeButton(e);
	}	
	
	public void calendarButton() {
		AlertControl.infoBox("Operation not available", "WARNING");
	}
	
	public void allQuestion() throws IOException {
		goToPage(NamePage.ALLQUESTION);
	}
	public void newQuestion() throws IOException {
		goToPage(NamePage.QUESTIONTYPE);
	}
	
	public void profileButton() {
		AlertControl.infoBox("Operation not available", "WARNING");
	}
	public void logOutButton() throws IOException {
		if(AlertControl.confirmation()) {
			goToPage(NamePage.LOGIN);
		}
	}
			
}