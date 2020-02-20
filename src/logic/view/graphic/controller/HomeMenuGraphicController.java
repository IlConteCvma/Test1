package logic.view.graphic.controller;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;

import logic.view.AlertControl;
import logic.view.NamePage;



public class HomeMenuGraphicController extends GraphicController{
	
	private static final String WARNING = "COMING SOON";
	private static final String NOTAVAILABLE = "Operation not available";
	
	@FXML
	public void homeButton(ActionEvent e){
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
	
	public void logOutButton(){
		if(AlertControl.confirmation()) {
			goToPage(NamePage.LOGIN);
			
			
		}
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing to do
		
	}

	
	
	
}
